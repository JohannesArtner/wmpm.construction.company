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
        public RouteBuilder route() {
            return new SocialMediaPublisher();
        }
    }

    private static final String fromEndpoint = "direct:createSocialMediaPost";
    private static final String toMockEnpoint = "mock:twitter";
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
                .response("response")
                .responseNotes("responseNote")
                .customerName("customerName")
                .customerMail("customerMail")
                .projectManagerId("projectManagerId")
                .build();
    }

    @DirtiesContext
    @Test
    public void offerAcception_success() throws Exception {
        resultEndpoint.expectedBodiesReceived(validOA);
        template.sendBody(validOA);
        resultEndpoint.assertIsSatisfied();
    }

    /*
    @Test
    @DirtiesContext
    public void validateFormInput_shouldFail_noEmail() throws Exception {
        errorEndpoint.expectedBodiesReceived("Invalid Form data: No Email set");
        template.sendBody(invalidOA);
        errorEndpoint.assertIsSatisfied();
    }*/
}