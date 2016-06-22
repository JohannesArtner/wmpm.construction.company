package com.routes.offerProcessor.routes;

import com.routes.offerProcessor.processors.AcceptedOfferPersistor;
import com.routes.offerProcessor.processors.EnrichProjectAggregationStrategy;
import com.routes.offerProcessor.processors.IncomingMailProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mionisation on 5/21/16.
 */
@Component
public class OfferAcceptedRoute extends RouteBuilder {
    @Autowired
    AcceptedOfferPersistor acceptedOfferPersistor;
    AggregationStrategy aggregationStrategy;
    @Override
    public void configure() throws Exception {
        aggregationStrategy = new EnrichProjectAggregationStrategy();
        from("direct:newOfferAccepted").process(new IncomingMailProcessor())

               .pollEnrich("file:testdata?fileName=projectManager.txt&readLock=none", aggregationStrategy)
                .process(acceptedOfferPersistor)
                //save offers as finished //rejected and unpublished
                .to("direct:offerMulticast");
    }
}
