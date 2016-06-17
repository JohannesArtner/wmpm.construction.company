package com.routes.offerProcessor.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by mionisation on 6/9/16.
 * This forwards the results of accepted offers to GoogleCalendar,
 * where new dates are registered, as well as to Social Media
 */
@Component
public class OfferResultMulticastRoute extends RouteBuilder{
    @Override
    public void configure() throws Exception {
        from("direct:offerMulticast")
                .multicast().parallelProcessing()
                //.to("direct:createGoogleCalendar")
                .to("direct:createSocialMediaPost");
    }
}