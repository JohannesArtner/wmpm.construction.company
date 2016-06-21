package com.routes.requestInput.routes;

import com.routes.requestInput.exception.NormalizationException;
import com.routes.requestInput.processor.LoggerProcessor;
import com.routes.requestInput.processor.ReouteRequestNormalizerFailureHandler;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Created by rudolfplettenberg on 19.05.16.
 */
@Component
public class RouteRequestNormalizer extends AbstractRestRouteBuilder {
    @Override
    public void configure() throws Exception {
        onException(NullPointerException.class).process(new ReouteRequestNormalizerFailureHandler()).stop();
        onException(NormalizationException.class).setBody(simple("${exception.message}")).to("direct:normalizationError");
        from("direct:requestNormalizerQueue").routeId("routeRequestNormalizer")
                .log("Starting normalization")
                .choice()
                    .when(header("origin").isEqualTo("form"))
                        .log("'origin' = 'form")
                        .to("bean:requestNormalizer?method=formToRequest")
                    .when(header("origin").isEqualTo("email"))
                        .log("'origin' = 'email")
                        .to("bean:requestNormalizer?method=emailToRequest")
                    .end()
                .log("Finished Normalization of Message")
                .process(new LoggerProcessor())
        .to("direct:requestPersistance");
    }
}
