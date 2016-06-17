package com.routes.requestProcessor.routes;

import com.database.projectDB.model.Offer;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.Synchronization;
import org.apache.camel.spi.UnitOfWork;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by Johannes on 24.05.2016.
 */
public class OfferViaEmail  extends RouteBuilder {
    static Logger logger = Logger.getLogger(OfferViaEmail.class);

    @Autowired
    private CamelContext context;

    @Override
    public void configure() throws Exception {
        logger.info("Route To send Email with offer to the customer.");

        Endpoint dBEndpoint = context.getEndpoint("jpa://com.database.projectDB.model.Offer?consumer.namedQuery=selectAllOpen");
        dBEndpoint.start();
        PollingConsumer consumer = dBEndpoint.createPollingConsumer();
        consumer.start();
        Exchange exchange = consumer.receive();

        logger.info("The polling-consumer is polling the Offers, if he finds one, he will send an email to the Customer!");

        Endpoint pdfEndpoint = context.getEndpoint("direct:offerToPdf");
        Producer producer = pdfEndpoint.createProducer();
        producer.start();
        producer.process(exchange);
    }

    public static void main(String[] args){
        try {
            OfferViaEmail offerViaEmail = new OfferViaEmail();
            CamelContext context = new DefaultCamelContext();
            offerViaEmail.context = context;
            offerViaEmail.configure();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
