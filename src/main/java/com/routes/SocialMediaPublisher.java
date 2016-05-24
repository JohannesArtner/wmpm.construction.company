package com.routes;

import com.Processors.LoggingProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

public class SocialMediaPublisher extends RouteBuilder {

    @Override
    public void configure() throws Exception {

                //https://graph.facebook.com/oauth/access_token?client_id = YOUR_APP_ID & client_secret =YOUR_APP_SECRET&grant_type = client_credentials
                //.setHeader("CamelHttpUri", constant("https://graph.facebook.com/endpoint?key=value&amp;access_token=1556856541283678|6b5f95106e108e957bf7f2c42b4bd3a9"))
                //.to("https://www.facebook.com")

        from("direct:foo")
        //multicast().parallelProcessing()

                .to("twitter://timeline/user?consumerKey=CrVdcIxI1s9vGo6lYM2AB3icG&consumerSecret=sqnGHKHmFBbtOSB1BwA0Trfiyoi0pSg1bMNUhnOXYEwBeFPuat&accessToken=734281955454427136-tNvFD0XS89Yi0QdSgghCeuuRFyERaOj&accessTokenSecret=rFpmvLGSOd4HCtYlcSfHPUQgrw3SGzbAvv8MM4zkXo1I8")

                .to("facebook://postFeed?inBody=postUpdate")
                .to("facebook://endpoint[?options]")
                .convertBodyTo(String.class)
                .to("log:FacebookDump")
                .to("mock:finish");

        ;
    }
}

