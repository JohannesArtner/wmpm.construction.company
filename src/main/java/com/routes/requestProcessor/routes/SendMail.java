package com.routes.requestProcessor.routes;

import com.routes.requestProcessor.processors.EmailProcessor;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;


/**
 * Created by Johannes on 10.06.2016.
 */
@org.springframework.stereotype.Component
public class SendMail extends RouteBuilder {
    static Logger logger = Logger.getLogger(SendMail.class);

    @Autowired
    public CamelContext context;

    @Autowired
    public EmailProcessor emailProcessor;

    @Override
    public void configure() throws Exception {
        from("seda:sendViaEmail").process(emailProcessor);
    }
}
