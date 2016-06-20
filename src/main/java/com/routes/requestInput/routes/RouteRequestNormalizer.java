package com.routes.requestInput.routes;

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

    static Logger logger = Logger.getLogger(RouteRequestFormInputToNormalizer.class.getName());

    @Override
    public void configure() throws Exception {
        logger.info("Route from Normalizer to Database");
        onException(NullPointerException.class).process(new ReouteRequestNormalizerFailureHandler()).stop();
        from("seda:requestNormalizerQueue").routeId("routeRequestNormalizer")
                .transacted()
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
        .to("seda:requestPersistance");
    }
}
