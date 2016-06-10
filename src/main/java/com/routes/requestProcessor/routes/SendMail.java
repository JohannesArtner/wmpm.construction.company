package com.routes.requestProcessor.routes;

import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

/**
 * Created by Johannes on 10.06.2016.
 */
public class SendMail extends RouteBuilder {
    static Logger logger = Logger.getLogger(OfferToDatabase.class);

    @Autowired
    private CamelContext context;

    @Override
    public void configure() throws Exception {

        Endpoint endpoint = context.getEndpoint("imaps://imap.gmail.com?username=offerManagementConstructionCom@gmail.com&password=supersafepw");

        Exchange exchange = endpoint.createExchange();
        Message in = exchange.getIn();
        in.setBody("Hello Friend");
        in.addAttachment("offer.pdf", new DataHandler(new FileDataSource("c:/Temp/camel/offerToPdf/offer.pdf")));

        Producer producer = endpoint.createProducer();
        producer.start();
        producer.process(exchange);
    }
}
