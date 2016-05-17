package com.routes;

import com.Beans.TransformationBean;
import com.Processors.LoggingProcessor;
import org.apache.camel.builder.RouteBuilder;


/**
 * Created by VanHelsing on 10.05.2016.
 */
public class IntegrationRoute extends RouteBuilder {

    @Override

    public void configure() throws Exception {

        from("file:target/inbox")

                .process(new LoggingProcessor())

                .bean(new TransformationBean(), "makeUpperCase")

                .unmarshal().csv()

                .split(body().tokenize(","))

                .choice()

                .when(body().contains("DVD"))

                .to("file:target/outbox/dvd")

                .when(body().contains("CD"))

                .to("activemq:CD_Orders")

                .otherwise()

                .to("mock:others");
    }

}