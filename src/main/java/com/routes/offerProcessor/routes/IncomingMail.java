package com.routes.offerProcessor.routes;

import com.routes.offerProcessor.processors.IncomingMailProcessor;
import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.component.google.mail.GoogleMailConfiguration;
import org.springframework.stereotype.Component;

/**
 * Created by mionisation on 5/24/16.
 */
@Component
public class IncomingMail extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        IncomingMailProcessor imp = new IncomingMailProcessor();
        //poll gmail inbox every 10 seconds
        //user: offerManagementConstructionCom
        //pass: supersafepw
        //process and route it
        from("imaps://imap.gmail.com?username=offerManagementConstructionCom@gmail.com&password=supersafepw"
                + "&delete=false&unseen=true&consumer.delay=10000").process(imp).to("seda:newOfferReply");
    }
}
