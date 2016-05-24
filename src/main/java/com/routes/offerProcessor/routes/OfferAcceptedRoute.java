package com.routes.offerProcessor.routes;

import com.routes.offerProcessor.processors.AcceptedOfferPersistor;
import com.routes.offerProcessor.processors.EnrichProjectAggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Component;

/**
 * Created by mionisation on 5/21/16.
 */
public class OfferAcceptedRoute extends RouteBuilder {
    AggregationStrategy aggregationStrategy;
    @Override
    public void configure() throws Exception {
        aggregationStrategy = new EnrichProjectAggregationStrategy();
        from("direct:newOfferAccepted")
                .enrich("direct:resource", aggregationStrategy)
                .process(new AcceptedOfferPersistor())
                .to("direct:resultToSocialMedia");
    }
}
