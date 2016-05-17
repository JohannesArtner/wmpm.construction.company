package com.Processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

/**
 * Created by VanHelsing on 10.05.2016.
 */
public class LoggingProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        System.out.println("Received Anything: " + exchange.getIn().getBody(String.class));

    }

}