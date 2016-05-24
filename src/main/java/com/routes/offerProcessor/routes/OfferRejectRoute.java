package com.routes.offerProcessor.routes;

import com.routes.offerProcessor.processors.RejectedOfferPersistor;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by mionisation on 5/24/16.
 */
public class OfferRejectRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:newOfferRejected")
                .process(new RejectedOfferPersistor()).end();
    }
}
