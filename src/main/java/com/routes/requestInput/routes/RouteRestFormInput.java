package com.routes.requestInput.routes;

import com.routes.requestInput.model.RestFormInputModel;
import com.routes.requestInput.processor.RequestValidationProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import static org.apache.camel.model.rest.RestParamType.body;

/**
 * Created by rudolfplettenberg on 19.05.16.
 */
@Component
public class RouteRestFormInput extends RouteBuilder {

    static Logger logger = Logger.getLogger(RouteRestFormInput.class.getName());

    @Override
    public void configure() throws Exception {
        logger.debug("Starting Route from Webservice to Validator");
        restConfiguration().component("undertow")
                // use json binding mode so Camel automatic binds json <--> pojo
                .bindingMode(RestBindingMode.json)
                // and output using pretty print
                .dataFormatProperty("prettyPrint", "true")
                // setup context path on localhost and port number that netty will use
                .contextPath("/").host("localhost").port(8080)
                // add swagger api-doc out of the box
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "User API").apiProperty("api.version", "1.2.3")
                // and enable CORS
                .apiProperty("cors", "true");


        rest("/request").description("Request rest service")
                .consumes("application/json").produces("application/json")


                .post().description("Post request").type(RestFormInputModel.class)
                .param().name("body").type(body).description("The form input of a request").endParam()
                .responseMessage().code(200).message("Request created").endResponseMessage()
                .to("direct:incomingForm");


        from("direct:incomingForm")
                .log("Incoming Request from a form")
                .process(new RequestValidationProcessor())
                .log("Request is valid")
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        //set Header origin to form
                        logger.info("Setting headers");
                        exchange.getIn().setHeader("origin", "form");
                        logger.info("Debug message body: "+exchange.getIn().getBody().toString());
                    }
                })
                .log("Added header")
                .to("seda:requestNormalizerQueue");
    }

}
