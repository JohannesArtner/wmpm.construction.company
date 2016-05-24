package com.routes.requestInput.routes;

import com.routes.requestInput.processor.RequestValidationProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Created by rudolfplettenberg on 19.05.16.
 */
@Component
public class RouteRequestFormInputToNormalizer extends RouteBuilder {

    static Logger logger = Logger.getLogger(RouteRequestFormInputToNormalizer.class.getName());

    @Override
    public void configure() throws Exception {
        from("direct:incomingForm")
                .log("Incoming Request from a form")
                .process(new RequestValidationProcessor())
                .log("Request is valid")
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        //set Header origin to form
                        logger.info("Setting headers");
                        exchange.getIn().setHeader("origin", "form");
                        logger.info("Debug message body: "+exchange.getIn().getBody().toString());
                    }
                })
                .log("Added header")
                .to("seda:requestNormalizerQueue");
    }

}
