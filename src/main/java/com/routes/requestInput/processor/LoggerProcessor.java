package com.routes.requestInput.processor;

import com.routes.requestInput.routes.RouteRestFormInput;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

/**
 * Just logs the current message
 */
public class LoggerProcessor implements Processor {
    static Logger logger = Logger.getLogger(RouteRestFormInput.class.getName());

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("-------------------------------");
        logger.info("Message logging:");
        logger.info("Header: "+exchange.getIn().getHeaders().toString());
        logger.info("Body: "+exchange.getIn().toString());
        logger.info("-------------------------------");


    }
}
