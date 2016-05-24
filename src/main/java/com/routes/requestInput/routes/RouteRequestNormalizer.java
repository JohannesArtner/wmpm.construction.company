package com.routes.requestInput.routes;

import com.routes.requestInput.processor.LoggerProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Created by rudolfplettenberg on 19.05.16.
 */
@Component
public class RouteRequestNormalizer extends RouteBuilder {

    static Logger logger = Logger.getLogger(RouteRequestFormInputToNormalizer.class.getName());

    @Override
    public void configure() throws Exception {
        logger.info("Rout from Normalizer to Database");
        //TODO Wird bei ReoutePollingIDFromDB entschieden bitte
        //from("seda:requestNormalizerQueue2") [vom ProutePolling]
        from("seda:requestNormalizerQueue")
                .log("Starting normalization")
                .choice()
                .when(header("origin").isEqualTo("form"))
                    .log("Form origin detected")
                    .to("bean:requestNormalizer?method=formToRequest")
                .when(header("origin").isEqualTo("email"))
                    .log("Email origin detected")
                    .to("bean:requestNormalizer?method=emailToRequest")
                .end()
                .log("Finished Normalization")
                .process(new LoggerProcessor())
        .to("seda:requestPersistance");
    }
}
