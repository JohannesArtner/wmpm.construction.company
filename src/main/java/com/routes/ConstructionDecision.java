package com.routes;

import com.Beans.TransformationBean;
import com.Processors.LoggingProcessor;
import com.services.ProcessRequest;
import com.services.RequestService;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by VanHelsing on 17.05.2016.
 */

@Component
public class ConstructionDecision extends RouteBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(ProcessRequest.class);

    @Autowired
    RequestService requestService;

    @Autowired
    ProcessRequest processor;

    @Override
    public void configure() throws Exception {

        from("direct:processRequest").process(processor).log("Request received: ${body.getId()}")
                .setBody().simple("{ \"id\": \"${body.getId()}\" }")
                .log("Request received (id): ${body.getId()}")
                .choice()
                .when(body().contains("Tiefbau"))
                .to("bean:kostenvoranschlagsService?method=makeForTiefbau(${body.getId()})")
                .when(body().contains("Hochbau"))
                .to("bean:kostenvoranschlagsService?method=makeForHochbau(${body.getId()})")
                .otherwise()
                .to("mock:others");
    }
}
