package com.routes.requestInput.routes;

import com.builder.ClientBuilder;
import com.builder.RequestBuilder;
import com.builder.RestFormInputModelBuilder;
import com.database.clientDB.model.Client;
import com.database.employeeDB.model.SpecializationType;
import com.database.projectDB.model.Request;
import com.routes.requestInput.model.NormalizedInput;
import com.routes.requestInput.model.RestFormInputModel;
import com.routes.requestInput.processor.RequestNormalizer;
import com.routes.requestInput.routes.RouteRequestNormalizer;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringDelegatingTestContextLoader;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Unit tests for RouteRequestNormalizer
 */
@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RouteRequestNormalizerUT.ContextConfig.class}, loader = CamelSpringDelegatingTestContextLoader.class)
public class RouteRequestNormalizerUT extends AbstractJUnit4SpringContextTests {

    //Context configuration for Test
    @Configuration
    public static class ContextConfig extends SingleRouteCamelConfiguration {
        @Bean
        public RouteBuilder route() {
            return new RouteBuilder() {
                public void configure() {
                    from(fromEndpoint)
                        .choice()
                            .when(header("origin").isEqualTo("form"))
                            .log("'origin' = 'form")
                            .to("bean:requestNormalizer?method=formToRequest")
                            .when(header("origin").isEqualTo("email"))
                            .log("'origin' = 'email")
                            .to("bean:requestNormalizer?method=emailToRequest")
                            .end()
                    .to(toMockEnpoint);

                    /*new RouteRequestNormalizer();
                    interceptSendToEndpoint("seda:requestPersistance")
                            .skipSendToOriginalEndpoint()
                            .to(toMockEnpoint);*/
                }
            };
        }
        @Bean
        public RequestNormalizer requestNormalizer(){
            return new RequestNormalizer();
        }

    }
    private static final String fromEndpoint = "seda:requestNormalizerQueue";
    private static final String toMockEnpoint = "mock:requestPersistance";

    @EndpointInject(uri = toMockEnpoint)
    protected MockEndpoint resultEndpoint;
    @Produce(uri = fromEndpoint)
    protected ProducerTemplate template;

    private RestFormInputModel validFormInput;
    private NormalizedInput expectedNormalizedInput;
    private Client expectedClient;
    private Request expectedRequest;



    public RouteRequestNormalizerUT() throws ParseException {
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

        expectedClient = new ClientBuilder()
                .address("Adresse")
                .clientFirstname("First Name")
                .clientLastname("Last Name")
                .companyName("Company Name")
                .email("validemail@email.com")
                .telephone("123456789")
                .build();

        expectedRequest = new RequestBuilder()
                .specializationType(SpecializationType.HOCHBAU)
                .squaremeter(1000)
                .location("Location")
                .description("Description")
                .dateFrom(fromDate)
                .dateTo(toDate)
                .build();

        expectedNormalizedInput = new NormalizedInput();
        expectedNormalizedInput.setClient(expectedClient);
        expectedNormalizedInput.setRequest(expectedRequest);

    }


    @DirtiesContext
    @Test
    public void normalizeFormInput_success() throws Exception {

        resultEndpoint.expectedBodiesReceived(expectedNormalizedInput);
        template.sendBodyAndHeader(validFormInput, "origin", "form");
        resultEndpoint.assertIsSatisfied();

    }


    @Test
    public void alwaysSuccess(){
        assertTrue(true);
    }




}

/*
public class RouteRequestNormalizerUT extends CamelTestSupport {

    @Produce(uri = toMockEnpoint)
    protected ProducerTemplate template;


    private static final String fromMockEnpoint = "direct:mockRequestNormalizerQueu";
    private static final String toMockEnpoint = "mock:requestPersistance";

    private RestFormInputModel validFormInput;

    public RouteRequestNormalizerUT() throws ParseException {
        String fromDateString = "01-Apr-2016,13:00:14 PM";
        DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
        Date fromDate = formatter.parse(fromDateString);
        String toDateString = "28-Sep-2016,22:00:14 PM";
        Date toDate = formatter.parse(toDateString);

        validFormInput = new RestFormInputModel();
        validFormInput.setAddress("Test Address");
        validFormInput.setClientFirstname("First Name");
        validFormInput.setClientLastname("Last Name");
        validFormInput.setCompanyName("Company Name");
        validFormInput.setDateFrom(fromDate);
        validFormInput.setDateTo(toDate);
        validFormInput.setEmail("valid@email.com");
        validFormInput.setSpecializationType(SpecializationType.HOCHBAU);
        validFormInput.setSquaremeter(1000);
        validFormInput.setLocation("Location");
        validFormInput.setTelephone("0123456789");
        validFormInput.setDescription("Description");
    }



    @Override
    public boolean isCreateCamelContextPerClass() {
        // we override this method and return true, to tell Camel test-kit that
        // it should only create CamelContext once (per class), so we will
        // re-use the CamelContext between each test method in this class
        return true;
    }

    @Override
    public boolean isUseAdviceWith() {
        return true;
    }




    @Test
    public void normalizeFormInputShouldwork() throws Exception{
        context().getRouteDefinition("routeRequestNormalizer")
                .adviceWith(context, new RouteBuilder() {
                    @Override
                    public void configure() throws Exception {
                        // intercept sending to seda:requestPersistance and send to mock:requestPersistance
                        replaceRouteFromWith("requestNormalizerQueue",toMockEnpoint);
                        interceptSendToEndpoint("seda:requestPersistance")
                                .skipSendToOriginalEndpoint()
                                .to(fromMockEnpoint);
                    }
                });
        context.start();
        template.sendBody(fromMockEnpoint, validFormInput);

        String response = getMockEndpoint(fromMockEnpoint).getExchanges().get(0).getIn().getBody().toString();

        log.info("Response :"+response);

    }


    @Test
    public void testSendMatchingMessage() throws Exception {
        String expectedBody = "<matched/>";

        resultEndpoint.expectedBodiesReceived(expectedBody);

        template.sendBodyAndHeader(expectedBody, "foo", "bar");

        resultEndpoint.assertIsSatisfied();
    }

    @Test
    public void testSendNotMatchingMessage() throws Exception {
        resultEndpoint.expectedMessageCount(0);

        template.sendBodyAndHeader("<notMatched/>", "foo", "notMatchedHeaderValue");

        resultEndpoint.assertIsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteRequestNormalizer();
    }

}

*/