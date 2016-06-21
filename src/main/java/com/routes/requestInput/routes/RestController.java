package com.routes.requestInput.routes;

import com.database.clientDB.model.Client;
import com.database.employeeDB.model.ProjectManager;
import com.database.projectDB.model.Offer;
import com.database.projectDB.model.Request;
import com.fasterxml.jackson.core.JsonParseException;
import com.routes.requestInput.exception.RequestValidationException;
import com.routes.requestInput.model.RestFormInputModel;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import static org.apache.camel.model.rest.RestParamType.body;

/**
 * Rest API Controller
 */
@Component
public class RestController extends AbstractRestRouteBuilder {
    static Logger logger = Logger.getLogger(RestController.class.getName());

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


        // /requests
        rest("/requests").description("Request rest service")
                .consumes("application/json").produces("application/json")
                .post().description("Post request").type(RestFormInputModel.class)
                    .param().name("body").type(body).description("The form input of a request").endParam()
                    .responseMessage().code(200).message("Request created").endResponseMessage()
                    .to("direct:incomingForm")
                .get().description("Get all Requests").outTypeList(Request.class)
                    .to("mongodb:myDb?database=test&collection=request&operation=findAll");

        // /offers
        rest("/offers").description("Offer rest service")
                .consumes("application/json").produces("application/json")
                .get().description("Get all Offers").outTypeList(Offer.class)
                    .to("mongodb:myDb?database=test&collection=offer&operation=findAll");

        // /projects
        rest("/projects").description("Project rest service")
                .consumes("application/json").produces("application/json")
                .get().description("Get all Projects").outTypeList(Offer.class)
                    .to("mongodb:myDb?database=test&collection=projects&operation=findAll");


        // /clients
        rest("clients").description("Client Rest Service")
                .consumes("application/json").produces("application/json")

                .get().description("Get all Clients").outTypeList(Client.class)
                    .to("bean:clientDAO?method=findAll()")

                .post().description("Post client").type(RestFormInputModel.class)
                    .param().name("body").type(body).description("Client to save in the DB").endParam()
                    .responseMessage().code(200).message("Client created").endResponseMessage()
                    .to("jpa://com.database.clientDB.model.Client");


        //Project manager Rest
        rest("pm").description("Projectmanager Rest Service")
                .consumes("application/json").produces("application/json")

                .get().description("Get all Clients").outTypeList(ProjectManager.class)
                    .to("bean:employeeDAO?method=findAllProjectManagers()");

    }
}
