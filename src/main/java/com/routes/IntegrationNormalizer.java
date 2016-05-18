package com.routes;

import com.Processors.LoggingProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by VanHelsing on 17.05.2016.
 */
@Component
public class IntegrationNormalizer extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // we need to normalize two types of incoming messages
        from("imap://admin@mymailserver.com?password=secret&unseen=true&consumer.delay=60000")
    .process(new LoggingProcessor())
    .choice()
    .when().xpath("/employee").to("bean:normalizer?method=employeeToPerson")
    .when().xpath("/customer").to("bean:normalizer?method=customerToPerson")
    .end()
    .to("file:target/inbox");
    }
}
