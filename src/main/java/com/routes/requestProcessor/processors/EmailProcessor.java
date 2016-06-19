package com.routes.requestProcessor.processors;

import org.apache.camel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.util.ByteArrayDataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Johannes on 17.06.2016.
 */
@org.springframework.stereotype.Service
public class EmailProcessor implements Processor{
    private static final Logger log = LoggerFactory.getLogger(EmailProcessor.class);

    @Autowired
    private CamelContext context;

    @Override
    public void process(Exchange exchange) throws Exception {

        log.info("Initializing Email Service");

        Endpoint endpointEmail = context.getEndpoint("smtp://smtp.gmail.com?port=587&username=offerManagementConstructionCom@gmail.com&password=supersafepw&mail.smtp.auth=true&mail.smtp.starttls.enable=true");
        endpointEmail.start();

        Properties props = new Properties();
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.googlemail.com");
        props.put("mail.smtp.port", "587");
        Authenticator auth = null;
        props.put("mail.smtp.auth", "true");
        auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("offerManagementConstructionCom@gmail.com", "supersafepw");
            }
        };
        Session t = Session.getInstance(props, auth);

        Message in = exchange.getIn();
        String body = "Hello Customer!\nHere is your offer.\nIt is a good one!\n\nRegards The constructors.";
        in.setBody(body);

        String filePath = (String) exchange.getIn().getHeader(Exchange.FILE_PATH);
        String fileName = (String) exchange.getIn().getHeader(Exchange.FILE_NAME);
        in.addAttachment(fileName, new DataHandler(new FileDataSource(filePath)));
        byte[] file = in.getBody(byte[].class);
        String fileId = in.getHeader("CamelFileName", String.class);
        in.addAttachment(fileId, new DataHandler(new ByteArrayDataSource(file, "application/pdf")));


        ProducerTemplate template = context.createProducerTemplate();
        template.start();
        exchange = endpointEmail.createExchange();

        Map<String, Object> headers = null;
        headers = new HashMap<String, Object>();
        headers.put("Subject", "This is your special Offer!");
        headers.put("To", "johannes.artner@gmx.at");
        headers.put("From", "offerManagementConstructionCom@gmail.com");

        in.setHeaders(headers);

        exchange.setOut(exchange.getIn());

        template.send(endpointEmail, exchange);
        template.sendBodyAndHeaders(endpointEmail, body, headers);
    }

}
