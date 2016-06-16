package com.routes.offerProcessor.routes;

import com.routes.offerProcessor.processors.IncomingMailProcessor;
import com.routes.offerProcessor.processors.RejectedOfferPersistor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mionisation on 5/24/16.
 */
@Component
public class OfferRejectRoute extends RouteBuilder {
    @Autowired
    RejectedOfferPersistor rejectedOfferPersistor;
    @Override
    public void configure() throws Exception {
        IncomingMailProcessor imp = new IncomingMailProcessor();

        from("direct:newOfferRejected").process(imp)
                .process(rejectedOfferPersistor).end();
    }
}
