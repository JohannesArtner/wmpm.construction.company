package com.routes.requestProcessor.routes;

import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Johannes on 10.06.2016.
 */
public class SendMail extends RouteBuilder {
    static Logger logger = Logger.getLogger(OfferToDatabase.class);

    @Autowired
    public CamelContext context;

    @Override
    public void configure() throws Exception {

        Endpoint endpoint = context.getEndpoint("smtp://smtp.gmail.com?port=587&username=offerManagementConstructionCom@gmail.com&password=supersafepw&mail.smtp.auth=true&mail.smtp.starttls.enable=true");
        endpoint.start();


        log.info("Initializing Email Service");

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


        ProducerTemplate template = context.createProducerTemplate();
        template.start();
        Exchange exchange = endpoint.createExchange();
        Message in = exchange.getIn();
        String body = "Hello Customer!\nHere is your offer.\nIt is a good one!\n\nRegards The constructors.";
        in.setBody(body);
        in.addAttachment("offer.pdf", new DataHandler(new FileDataSource("c:/Temp/camel/offerToPdf/offer.pdf")));

        Map<String, Object> headers = null;
        headers = new HashMap<String, Object>();
        headers.put("Subject", "This is your special Offer!");
        headers.put("To", "johannes.artner@gmx.at");
        headers.put("From", "offerManagementConstructionCom@gmail.com");
        template.sendBodyAndHeaders(endpoint, body, headers);
    }

    public static void main(String[] args){
        try {
            SendMail sendMail = new SendMail();
            CamelContext context = new DefaultCamelContext();
            sendMail.context = context;
            sendMail.configure();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
