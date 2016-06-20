package com.routes.requestProcessor.routes;

import com.routes.requestProcessor.processors.ProcessOffer;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Johannes on 24.05.2016.
 */
@org.springframework.stereotype.Component
public class PollOffers extends RouteBuilder {
    static Logger logger = Logger.getLogger(PollOffers.class);

    @Autowired
    private CamelContext context;

    @Autowired
    private ProcessOffer processor;

    @Override
    public void configure() throws Exception {
        logger.info("Route To send Email with offer to the customer.");

        errorHandler(deadLetterChannel("jms:queue:dead"));

        from("jpa://com.database.projectDB.model.Offer?consumer.namedQuery=selectAllOpen").process(processor).to("seda:offerToPdf");
    }
}
