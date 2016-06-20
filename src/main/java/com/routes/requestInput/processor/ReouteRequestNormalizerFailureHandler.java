package com.routes.requestInput.processor;

import com.database.clientDB.ClientDAO;
import com.database.clientDB.model.Client;
import com.database.projectDB.RequestDAO;
import com.routes.requestInput.model.NormalizedInput;
import com.routes.requestInput.routes.RouteRequestFormInputToNormalizer;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Wailzer
 *
 * Receive ID from DB if possible
 */
@Service
public class ReouteRequestNormalizerFailureHandler implements Processor {
    static Logger logger = Logger.getLogger(RouteRequestFormInputToNormalizer.class.getName());

    public void process(Exchange exchange) throws Exception {
        // the caused by exception is stored in a property on the exchange
        Throwable caused = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
        //assertNotNull(caused);
        logger.info("ReouteRequestNormalizerFailureHandler Error: " + caused);
        exchange.getContext().createProducerTemplate().send("mock:myerror", exchange);
        }
}
