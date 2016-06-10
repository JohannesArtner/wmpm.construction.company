package com.routes.requestProcessor.routes;

import com.routes.requestProcessor.processors.DatabaseOfferProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Johannes on 23.05.2016.
 */
@Component
public class OfferToDatabase extends RouteBuilder{
        static Logger logger = Logger.getLogger(OfferToDatabase.class);

        @Autowired
        DatabaseOfferProcessor databaseOfferProcessor;

        @Override
        public void configure() throws Exception {
            logger.info("Route To persist offer data");

            from("seda:makeOfferPersistent")
                    .log("Persisting Data")
                    .process(databaseOfferProcessor)
                    .end();
        }
}
