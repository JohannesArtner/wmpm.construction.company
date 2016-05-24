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
        logger.info("Body: "+exchange.getIn().toString());
        logger.info("Attachements: "+exchange.getIn().getAttachmentNames().toString());
        logger.info("-------------------------------");


    }
}
