package com.routes.offerProcessor.routes;

import com.Beans.TransformationBean;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by mionisation on 5/24/16.
 */
public class OfferDecisionRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("seda:newOfferReply")
                .bean(new TransformationBean(), "makeUpperCase")
                .split(body().tokenize(","))
                .choice()
                .when(body().contains("OK"))
                .log("OFFER IS ACCEPTED")
                .to("direct:newOfferAccepted")
                .otherwise()
                .log("OFFER IS REJECTED")
                .to("direct:newOfferRejected");
    }
}
