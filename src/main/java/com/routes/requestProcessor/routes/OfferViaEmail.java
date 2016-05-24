package com.routes.requestProcessor.routes;

import com.routes.requestProcessor.processors.DatabaseOfferProcessor;
import com.routes.requestProcessor.processors.OfferEmailProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Johannes on 24.05.2016.
 */
public class OfferViaEmail  extends RouteBuilder {
    static Logger logger = Logger.getLogger(OfferToDatabase.class);

    @Autowired
    OfferEmailProcessor offerEmailProcessor;

    @Override
    public void configure() throws Exception {
        logger.info("Route To send Email with offer to the customer.");

        //Polling Consumer from Database

        from("seda:makeOfferPersistent")
                .log("creating a PDF from offer")
                .process(offerEmailProcessor)
                .end();
    }
}
