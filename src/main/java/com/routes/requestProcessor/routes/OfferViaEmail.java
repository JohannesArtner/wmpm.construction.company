package com.routes.requestProcessor.routes;

import com.database.projectDB.model.Offer;
import com.routes.requestProcessor.processors.DatabaseOfferProcessor;
import com.routes.requestProcessor.processors.OfferEmailProcessor;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
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
    static Logger logger = Logger.getLogger(OfferToDatabase.class);

    @Autowired
    private CamelContext context;

    @Override
    public void configure() throws Exception {
        logger.info("Route To send Email with offer to the customer.");

        Endpoint dBEndpoint = context.getEndpoint("jpa://com.database.projectDB.model.Offer?consumer.nativeQuery=select * from Offer");
        PollingConsumer consumer = dBEndpoint.createPollingConsumer();
        Exchange exchange = consumer.receive(1);

        logger.info("The polling-consumer is polling the Offers, if he finds one, he will send an email to the Customer!");
        Offer offer = (Offer) exchange.getOut().getBody();

        from("direct:createPdfOffer").to("direct:offerToPdf");
    }
}
