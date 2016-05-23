package com.routes.requestInput.routes;

import com.Beans.TransformationBean;
import com.Processors.LoggingProcessor;
import org.apache.camel.builder.RouteBuilder;


/**
 * Created by Wailzer
 */
public class RequestIntegrationRoute extends RouteBuilder {

    @Override

    public void configure() throws Exception {

        from("file:target/inbox")

                .process(new LoggingProcessor())

                .bean(new TransformationBean(), "makeUpperCase")

                .unmarshal().csv()

                .split(body().tokenize(","))

                .choice()

                .when(body().contains("Hochbau"))

                .to("direct:REQUEST_TYPE_2")

                .when(body().contains("Tiefbau"))

                .to("direct:REQUEST_TYPE_1")

                .otherwise()

                .to("mock:others");
    }

}