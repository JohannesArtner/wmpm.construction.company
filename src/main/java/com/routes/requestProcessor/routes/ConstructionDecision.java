package com.routes.requestProcessor.routes;

import com.routes.requestProcessor.processors.ProcessRequest;
import com.services.RequestService;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Johannes
 */

@Component
public class ConstructionDecision extends RouteBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(ConstructionDecision.class);

    @Autowired
    RequestService requestService;

    @Autowired
    ProcessRequest processor;


    @Override
    public void configure() throws Exception {

        errorHandler(deadLetterChannel("jms:queue:dead"));

        from("direct:processRequest").process(processor).log("Request received!")
                .choice()
                .when(header("constructiontype").isEqualTo("tiefbau"))
                .log("Tiefbau was found and is now going to be processed!")
                .to("bean:kostenvoranschlagsService?method=makeForTiefbau(${body})")
                .when(header("constructiontype").isEqualTo("hochbau"))
                .log("Hochbau was found and is now going to be processed")
                .to("bean:kostenvoranschlagsService?method=makeForHochbau(${body})")
                .otherwise()
                .log("there is one request which is neither tiefbau nor hochbau!")
                .to("jms:queue:dead");
    }
}
