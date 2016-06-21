package com.routes.socialMedia.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

public class ErrorProcessor implements Processor {

    static Logger logger = Logger.getLogger(ErrorProcessor.class.getName());

    @Override
    public void process(Exchange exchange) throws Exception {

        Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);

        if(cause != null){
            logger.error("Error has occurred: ", cause);

            // Sending Error message to client
            exchange.getOut().setBody("Error");
        }else

            // Sending response message to client
            exchange.getOut().setBody("Good");
    }
}