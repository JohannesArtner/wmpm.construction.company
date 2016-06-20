package com.test.test;

import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.junit.Assert.assertEquals;

public class RequestByMailTest extends CamelTestSupport {
    @EndpointInject(uri = "mock:seda:requestPersistance")
    protected MockEndpoint requestPersistance;

    @Produce(uri = "direct:start")
    protected ProducerTemplate template;

    @Override
    public boolean isDumpRouteCoverage() {
        return true;
    }

    @Test
    public void testSendMatchingMessage() throws Exception {
        String expectedBody = "ABC_Description#Hochbau#2016-06-18#2016-06-18#vienna#100#Buildwithme#Gregor#Build#his adress 45#068725884";

        //resultEndpoint.expectedBodiesReceived(expectedBody);

        template.sendBodyAndHeader(expectedBody, "foo", "bar");

       // resultEndpoint.assertIsSatisfied();
    }

    @Test
    public void testSendNotMatchingMessage() throws Exception {
        //resultEndpoint.expectedMessageCount(0);

        template.sendBodyAndHeader("<notMatched/>", "foo", "notMatchedHeaderValue");

        //resultEndpoint.assertIsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            public void configure() {
                from("direct:start").filter(header("foo").isEqualTo("bar")).to("mock:result");
            }
        };
    }
}