package com.routes;

import org.apache.camel.builder.RouteBuilder;

/**
 * Created by VanHelsing on 17.05.2016.
 */
public class WireTapNewClients extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:start")
                .wireTap("mock:tapped")
                .to("direct:");
    }
}
