package com.routes.requestInput.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

/**
 * Abstract class for Routes that comes from REST.
 */
public abstract class AbstractRestRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        onException(Exception.class)
                .setHeader("routeId", property(Exchange.FAILURE_ROUTE_ID))
                .setHeader("endpoint", property(Exchange.FAILURE_ENDPOINT))
                .setHeader("exception", property(Exchange.EXCEPTION_CAUGHT))
                .setHeader("subject", simple("Camel Error - ${exception.class.simpleName}"))
                .transform(simple("${exception.message}\n\nStacktrace Details:\n\n${exception.stacktrace}"))
                .to("file:errors/REST");
    }
}
