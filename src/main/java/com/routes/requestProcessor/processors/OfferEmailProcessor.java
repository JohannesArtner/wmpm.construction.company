package com.routes.requestProcessor.processors;

import com.database.projectDB.OfferDAO;
import com.database.projectDB.RequestDAO;
import com.database.projectDB.model.Offer;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Johannes on 24.05.2016.
 */
public class OfferEmailProcessor implements Processor {
    static Logger logger = Logger.getLogger(Processor.class.getName());

    @Autowired
    OfferDAO offerDAO;

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("Saving new Offer: "+exchange.getIn().toString());
        Offer offer = exchange.getIn().getBody(Offer.class);

        //TODO: create a pdf here!

        logger.info("Created a pdf successfully!");
    }
}
