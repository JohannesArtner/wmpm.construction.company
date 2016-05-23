package com.routes;

import com.Processors.EnrichProjectAggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Component;

/**
 * Created by mionisation on 5/21/16.
 */
@Component
public class OfferEnrichRoute extends RouteBuilder {
    AggregationStrategy aggregationStrategy;
    @Override
    public void configure() throws Exception {
        aggregationStrategy = new EnrichProjectAggregationStrategy();
        from("direct:start")
                .enrich("direct:resource", aggregationStrategy)
                .to("direct:result");
    }
}
