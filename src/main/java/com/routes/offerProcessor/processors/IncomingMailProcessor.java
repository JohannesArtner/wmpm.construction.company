package com.routes.offerProcessor.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

/**
 * Created by mionisation on 5/24/16.
 */
public class IncomingMailProcessor implements Processor {
    static Logger logger = Logger.getLogger(IncomingMailProcessor.class.getName());

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("-------------------------------");
        logger.info("Incoming Offer Mail:");
        logger.info("Header: "+exchange.getIn().getHeaders().toString());
        logger.info("Subject: "+exchange.getIn().getHeaders().get("subject").toString());
        logger.info("Body: "+exchange.getIn().getBody().toString());
        logger.info("-------------------------------");


    }
}
