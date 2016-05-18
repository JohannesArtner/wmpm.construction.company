package com.routes;

import com.Beans.TransformationBean;
import com.Processors.LoggingProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by VanHelsing on 17.05.2016.
 */

@Component
public class ConstructionDecision extends RouteBuilder {


    @Override
    public void configure() throws Exception {

        from("file:target/inbox")
                .process(new LoggingProcessor())

                .choice()

                .when(body().contains("Tiefbau"))

                .to("file://tiefbau_request_mgmt")

                .when(body().contains("Hochbau"))

                .to("file://hochbau_request_mgmt")

                .otherwise()

                .to("mock:others");
    }
}
