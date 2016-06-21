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
        from("direct:requestPersistance")
                .log("Persisting Data")
                .process(databaseProcessor)
            //    .log("wireTap to decision hochbau or tiefbau")
           //.wireTap("direct:processRequest")
                .log("sending request to testProcessor")
        .to("mock:somefurthersteps");
    }
}
