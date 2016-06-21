package com.routes.requestInput.routes;

import com.routes.requestInput.exception.RequestValidationException;
import com.routes.requestInput.processor.RequestValidationProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Route from incoming REST POST /form to normalizer
 *
 * Also includes validation
 */
@Component
public class RouteRequestFormInputToNormalizer extends AbstractRestRouteBuilder {

    @Override
    public void configure() throws Exception {
        onException(RequestValidationException.class)
                .log("RequestValidationException: ${exception.message}")
                .handled(true)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
                .setHeader(Exchange.CONTENT_TYPE, constant("text/plain"))
                .setBody().simple("Invalid Form data: ${exception.message}")
                .to("mock:direct:validationError");

        from("direct:incomingForm")
                .log("Incoming Request from a form")
                // Validation
                .process(new RequestValidationProcessor())
                .log("Request is valid")
                // If valid set headers
                .setHeader("origin",constant("form"))
                .log("Header origin set to 'form'")
                .to("seda:requestNormalizerQueue");
    }

}
