package com.routes.requestInput.routes;

import com.Beans.TransformationBean;
import com.Processors.LoggingProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;


/**
 * Created by Wailzer
 */
public class RequestIntegrationRoute extends RouteBuilder {

    static Logger logger = Logger.getLogger(RequestIntegrationRoute.class.getName());

    @Override

    public void configure() throws Exception {
        errorHandler(deadLetterChannel("file:errors"));
        logger.info("Route for the Hochbau or Tiefbau decision");

        from("file:target/inbox")
               // .errorHandler(loggingErrorHandler("com.errorlogger"))
                .process(new LoggingProcessor())

                .bean(new TransformationBean(), "makeUpperCase")
                .log("Transform Body")

                .unmarshal().soapjaxb()
                .log("Unmarshall to right Format")

                .split(body().tokenize(","))

                .choice()

                .when(body().contains("Hochbau"))
                .log("found Hochbau")

                .to("direct:REQUEST_TYPE_2")

                .when(body().contains("Tiefbau"))
                .log("found Tiefbau")

                .to("direct:REQUEST_TYPE_1")

                .otherwise()
                .log("nothing found, doing MOCK")

                .to("mock:others");
    }

}