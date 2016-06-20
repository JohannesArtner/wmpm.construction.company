package com.routes.requestInput.routes;

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

/**
 * Unittests for RouteRequestFormInputToNormalizer
 *
 * Created by rudolfplettenberg on 20.06.2016.
 */
@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RouteRequestFormInputToNormalizerUT.ContextConfig.class}, loader = CamelSpringDelegatingTestContextLoader.class)
@MockEndpoints
public class RouteRequestFormInputToNormalizerUT {
    //Context configuration for Test
    @Configuration
    public static class ContextConfig extends SingleRouteCamelConfiguration {
        @Bean
        public RouteBuilder route() {
            return new RouteRequestFormInputToNormalizer();
        }
    }

    private static final String fromEndpoint = "direct:incomingForm";
    private static final String toMockEnpoint = "mock:seda:requestNormalizerQueue";
    private static final String toMockErrorEndpoint = "mock:direct:validationError";


    @EndpointInject(uri = toMockEnpoint)
    protected MockEndpoint resultEndpoint;
    @EndpointInject(uri = toMockErrorEndpoint)
    protected MockEndpoint errorEndpoint;
    @Produce(uri = fromEndpoint)
    protected ProducerTemplate template;

    private RestFormInputModel validFormInput;
    private RestFormInputModel invalidFormInput_noEmail;
    private RestFormInputModel invalidFormInput_wrongEmail;
    private RestFormInputModel invalidFormInput_endDateBeforeStartDate;



    public RouteRequestFormInputToNormalizerUT() throws ParseException {
        String fromDateString = "01-Apr-2016,13:00:14 PM";
        DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
        Date fromDate = formatter.parse(fromDateString);
        String toDateString = "28-Sep-2016,22:00:14 PM";
        Date toDate = formatter.parse(toDateString);

        validFormInput = new RestFormInputModelBuilder()
                .address("Adresse")
                .clientFirstname("First Name")
                .clientLastname("Last Name")
                .companyName("Company Name")
                .email("validemail@email.com")
                .specializationType(SpecializationType.HOCHBAU)
                .squaremeter(1000)
                .location("Location")
                .telephone("123456789")
                .description("Description")
                .dateFrom(fromDate)
                .dateTo(toDate)
                .build();

        invalidFormInput_noEmail = new RestFormInputModelBuilder()
                .address("Adresse")
                .clientFirstname("First Name")
                .clientLastname("Last Name")
                .companyName("Company Name")
                .specializationType(SpecializationType.HOCHBAU)
                .squaremeter(1000)
                .location("Location")
                .telephone("123456789")
                .description("Description")
                .dateFrom(fromDate)
                .dateTo(toDate)
                .build();

        invalidFormInput_wrongEmail = new RestFormInputModelBuilder()
                .address("Adresse")
                .clientFirstname("First Name")
                .clientLastname("Last Name")
                .companyName("Company Name")
                .email("wrongEmailFormat")
                .specializationType(SpecializationType.HOCHBAU)
                .squaremeter(1000)
                .location("Location")
                .telephone("123456789")
                .description("Description")
                .dateFrom(fromDate)
                .dateTo(toDate)
                .build();

        invalidFormInput_endDateBeforeStartDate = new RestFormInputModelBuilder()
                .address("Adresse")
                .clientFirstname("First Name")
                .clientLastname("Last Name")
                .companyName("Company Name")
                .email("validemail@email.com")
                .specializationType(SpecializationType.HOCHBAU)
                .squaremeter(1000)
                .location("Location")
                .telephone("123456789")
                .description("Description")
                .dateFrom(toDate)
                .dateTo(fromDate)
                .build();
    }


    @DirtiesContext
    @Test
    public void validateFormInput_success() throws Exception {
        resultEndpoint.expectedBodiesReceived(validFormInput);
        resultEndpoint.expectedHeaderReceived("origin","form");
        template.sendBody(validFormInput);
        resultEndpoint.assertIsSatisfied();
    }

    @Test
    @DirtiesContext
    public void validateFormInput_shouldFail_noEmail() throws Exception {
        errorEndpoint.expectedBodiesReceived("Invalid Form data: No Email set");
        template.sendBody(invalidFormInput_noEmail);
        errorEndpoint.assertIsSatisfied();
    }

    @Test
    @DirtiesContext
    public void validateFormInput_shouldFail_wrongEmail() throws Exception {
        errorEndpoint.expectedBodiesReceived("Invalid Form data: Not a correct Email address");
        template.sendBody(invalidFormInput_wrongEmail);
        errorEndpoint.assertIsSatisfied();
    }

    @Test
    @DirtiesContext
    public void validateFormInput_shouldFail_endDateBeforeStartDate() throws Exception {
        errorEndpoint.expectedBodiesReceived("Invalid Form data: Start date is after endDate");
        template.sendBody(invalidFormInput_endDateBeforeStartDate);
        errorEndpoint.assertIsSatisfied();
    }
}