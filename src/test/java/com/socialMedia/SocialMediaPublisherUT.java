package com.socialMedia;

/**
 * Created by maxmueller on 21.06.16.
 */

import com.builder.OfferAcceptionModelBuilder;
import com.builder.RestFormInputModelBuilder;
import com.database.employeeDB.model.SpecializationType;
import com.routes.offerProcessor.model.OfferAcceptionModel;
import com.routes.requestInput.model.RestFormInputModel;
import com.routes.requestInput.routes.RouteRequestFormInputToNormalizer;
import com.routes.socialMedia.processors.TwitterProcessor;
import com.routes.socialMedia.routes.SocialMediaPublisher;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.apache.camel.test.spring.CamelSpringDelegatingTestContextLoader;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.builder.RestFormInputModelBuilder;
import com.database.employeeDB.model.SpecializationType;
import com.routes.requestInput.model.RestFormInputModel;
import com.routes.requestInput.processor.RequestNormalizer;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.ValidationException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.apache.camel.test.spring.CamelSpringDelegatingTestContextLoader;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;


@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SocialMediaPublisherUT.ContextConfig.class}, loader = CamelSpringDelegatingTestContextLoader.class)
@MockEndpoints
public class SocialMediaPublisherUT {
    //Context configuration for Test
    @Configuration
    public static class ContextConfig extends SingleRouteCamelConfiguration {
        @Bean
        public RouteBuilder route() { return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:createSocialMediaPost")
                        .process(new TwitterProcessor())
                        .log("TWITTER PROCESSOR REACHED; Call Twitter Route")
                        .to("mock:twitter://timeline/user?consumerKey=CrVdcIxI1s9vGo6lYM2AB3icG&consumerSecret=sqnGHKHmFBbtOSB1BwA0Trfiyoi0pSg1bMNUhnOXYEwBeFPuat&accessToken=734281955454427136-tNvFD0XS89Yi0QdSgghCeuuRFyERaOj&accessTokenSecret=rFpmvLGSOd4HCtYlcSfHPUQgrw3SGzbAvv8MM4zkXo1I8");
            }
        };
        }
        @Bean
        public TwitterProcessor twitterProcessor() {
            return new TwitterProcessor();
        }
    }

    private static final String fromEndpoint = "direct:createSocialMediaPost";
    private static final String toMockEnpoint = "mock:twitter://timeline/user?consumerKey=CrVdcIxI1s9vGo6lYM2AB3icG&consumerSecret=sqnGHKHmFBbtOSB1BwA0Trfiyoi0pSg1bMNUhnOXYEwBeFPuat&accessToken=734281955454427136-tNvFD0XS89Yi0QdSgghCeuuRFyERaOj&accessTokenSecret=rFpmvLGSOd4HCtYlcSfHPUQgrw3SGzbAvv8MM4zkXo1I8";
    private static final String toMockErrorEndpoint = "mock:direct:validationError";

    @EndpointInject(uri = toMockEnpoint)
    protected MockEndpoint resultEndpoint;
    @EndpointInject(uri = toMockErrorEndpoint)
    protected MockEndpoint errorEndpoint;
    @Produce(uri = fromEndpoint)
    protected ProducerTemplate template;

    private OfferAcceptionModel validOA;
    private OfferAcceptionModel invalidOA;


    public SocialMediaPublisherUT() throws ParseException {

        validOA = new OfferAcceptionModelBuilder()
                .projectId("projectId")
                .response("response")
                .responseNotes("responseNote")
                .customerName("customerName")
                .customerMail("customerMail")
                .projectManagerId("projectManagerId")
                .build();

        invalidOA = new OfferAcceptionModelBuilder()
                .projectId(null)
                .response(null)
                .responseNotes(null)
                .customerName(null)
                .customerMail("customerMail")
                .projectManagerId("projectManagerId")
                .build();
    }

    @DirtiesContext
    @Test
    public void offerAcception_success() throws Exception {
        resultEndpoint.expectedMessageCount(1);
        template.sendBody(validOA);
        resultEndpoint.assertIsSatisfied();
    }

    @Test
    @DirtiesContext
    public void offerAcception_failure() throws Exception {
        resultEndpoint.expectedMessageCount(0);
        template.sendBody(invalidOA);
        errorEndpoint.assertIsSatisfied();


    }
}