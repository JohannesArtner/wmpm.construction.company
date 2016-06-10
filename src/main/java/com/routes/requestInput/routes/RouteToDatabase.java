package com.routes.requestInput.routes;

import com.routes.requestInput.processor.DatabaseProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rudolfplettenberg on 19.05.16.
 */
@Component
public class RouteToDatabase extends AbstractRestRouteBuilder {
    static Logger logger = Logger.getLogger(RouteRequestFormInputToNormalizer.class.getName());

    @Autowired
    DatabaseProcessor databaseProcessor;

    @Override
    public void configure() throws Exception {
        logger.info("Route To persist request data");
        logger.info("Route from Normalizer to Database");
        from("seda:requestPersistance")
                .log("Persisting Data")
                .process(databaseProcessor)
         .log("save to inbox for further processing")
        .to("bean:testProcessor?method=testEndpoint(${body.getClient()},${body.getRequest()})");
                //.to("file:target/inbox") [Weiter zum ReqeustIntegrationRoute]
    }
}
