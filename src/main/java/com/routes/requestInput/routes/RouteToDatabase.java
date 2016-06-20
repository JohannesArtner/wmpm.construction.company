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
        onException(IllegalArgumentException.class).handled(true).to("file:target/inbox");
        from("seda:requestPersistance")
                .transacted()
                .log("Persisting Data")
                .process(databaseProcessor)
            //    .log("wireTap to decision hochbau or tiefbau")
           //.wireTap("direct:processRequest")
                .log("sending reqeust to testProcessor")
        .to("mock:somefurthersteps");

        //.to("bean:testProcessor?method=testEndpoint(${body.getClient()},${body.getRequest()})");
    }
}
