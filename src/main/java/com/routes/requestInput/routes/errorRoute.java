package com.routes.requestInput.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by rudolfplettenberg on 20.06.2016.
 */
@Component
public class errorRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:normalizationError")
                .log("Normalization Error recognized")
                .log("Body: {body}")
        .to("mock:noramlizationErrorHandling");
    }
}
