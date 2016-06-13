package com.routes.requestProcessor.routes;

import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Johannes on 10.06.2016.
 */
public class SendMail extends RouteBuilder {
    static Logger logger = Logger.getLogger(OfferToDatabase.class);

    @Autowired
    public CamelContext context;

    @Override
    public void configure() throws Exception {

        //Endpoint endpoint = context.getEndpoint("imaps://imap.gmail.com?username=offerManagementConstructionCom@gmail.com&password=supersafepw");

        Endpoint endpoint = context.getEndpoint("smtp://offerManagementConstructionCom@gmail.com?password=supersafepw");

        ProducerTemplate template = context.createProducerTemplate();

        Exchange exchange = endpoint.createExchange();
        Message in = exchange.getIn();
        in.setBody("Hello Friend");
        in.addAttachment("offer.pdf", new DataHandler(new FileDataSource("c:/Temp/camel/offerToPdf/offer.pdf")));

        String body = "Hello Customer.\nHere is your offer.\nIt is a good one!\n\nRegards The constructors.";

        in.setBody(body);
        in.setHeader("To", "johannes.artner@gmx.at");
        in.setHeader("From", "offerManagementConstructionCom@gmail.com");
        in.setHeader("Subject", "This is your special Offer!");

        Producer producer = endpoint.createProducer();
        producer.start();
        producer.process(exchange);
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
