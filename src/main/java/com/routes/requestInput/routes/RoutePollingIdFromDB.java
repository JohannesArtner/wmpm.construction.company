package com.routes.requestInput.routes;

import com.routes.requestInput.processor.DatabaseGetIDProcessor;
import com.routes.requestInput.processor.LoggerProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Daniel Wailzer
 */
/*
@Component
public class RoutePollingIdFromDB extends RouteBuilder {

    static Logger logger = Logger.getLogger(RouteRequestFormInputToNormalizer.class.getName());

    @Autowired
    DatabaseGetIDProcessor databaseGetIDProcessor;


    @Override
    public void configure() throws Exception {
        errorHandler(deadLetterChannel("seda:errors"));
        logger.info("Route for Polling ID from DB");
        from("seda:requestNormalizerQueue")
                .log("Getting Request from Form")
                //.filter()
                .process(databaseGetIDProcessor) //Returns Message + Offset
                .choice()
                .when(body().contains("ALREADY_EXISTS"))
                    .log("found client")
                    .to("file:target/inbox")
                .when(body().contains("CREATE_NEW"))
                    .log("new client")
                    .to("seda:requestNormalizerQueue")
                .end()
                .process(new LoggerProcessor())
        .to("seda:requestNormalizerQueue2");
    }
}
*/