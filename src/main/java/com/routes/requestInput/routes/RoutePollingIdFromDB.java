package com.routes.requestInput.routes;

import com.routes.requestInput.processor.LoggerProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Created by Daniel Wailzer
 */
@Component
public class RoutePollingIdFromDB extends RouteBuilder {

    static Logger logger = Logger.getLogger(RouteRestFormInput.class.getName());

    @Override
    public void configure() throws Exception {
        logger.info("Route for Polling ID from DB");
        from("direct:fromRequest")
                .log("Getting Request from Form")
                //.filter()
                .process(DatabaseGetIDProcessor) //Returns Message + Offset
                .choice()
                .when(body().contains("ALREADY_EXISTS"))
                    .log("found client")
                    .to("bean:requestNormalizer?method=formToRequest")
                .when(body().contains("CREATE_NEW"))
                    .log("new client")
                    .to("bean:requestNormalizer?method=emailToRequest")
                .end()
                .process(new LoggerProcessor())
        .to("direct:RouteNormalizer");
    }
}
