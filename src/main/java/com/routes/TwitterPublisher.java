package com.routes;

import com.Processors.LoggingProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

public class TwitterPublisher extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:foo")
                .to("twitter://timeline/user?consumerKey=CrVdcIxI1s9vGo6lYM2AB3icG&consumerSecret=sqnGHKHmFBbtOSB1BwA0Trfiyoi0pSg1bMNUhnOXYEwBeFPuat&accessToken=734281955454427136-tNvFD0XS89Yi0QdSgghCeuuRFyERaOj&accessTokenSecret=rFpmvLGSOd4HCtYlcSfHPUQgrw3SGzbAvv8MM4zkXo1I8");
        //multicast().parallelProcessing()
                //.to("file://order_mgmt")
                //.to("file://accounting");
    }
}
