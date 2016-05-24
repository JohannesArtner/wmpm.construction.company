package com.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by Johannes on 26.04.2016.
 */
public class RequestRoute extends RouteBuilder {
    public static final String CAMEL_FILE_NAME = "CamelFileName";

    @Override
    public void configure() throws Exception {

        /*from("{{requestSource}}")
                .log("got a new request")
                .choice()
                .when(p -> p.getIn().getHeader(CAMEL_FILE_NAME).toString()
                        .contains("hochbau"))
                .log("someone wants to build a Hochbau with us!")
                .to("{{hochbauEndpoint}}")
                .when(p -> p.getIn().getHeader(CAMEL_FILE_NAME).toString()
                        .contains("tiefbau"))
                .log("someone wants to build a Tiefbau with us!")
                .to("{{tiefbauEndpoint}}");*/
    }
}
