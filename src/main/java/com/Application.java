package com;
//import model.ClientRequest;
import com.TestBean.TestBean;
import com.services.ClientRequestService;
import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.log.LogComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import services.ClientRequestService;
import com.model.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.routes.*;

import java.util.Date;

/**
 * Created by mionisation on 4/27/16.
 */

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ClientRequestService clientRequestService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    private CamelContext camel;

    @Override
    public void run(String ... args){
        ClientRequest request = new ClientRequest("Max", "Mustermann", "max@muster.at", "Musterstraße 18/2, 1010 Wien", "Ich will ein neues Haus bauen!", new Date(), new Date());

        // create the camel context that is the "heart" of Camel
        camel = new DefaultCamelContext();
        camel.addRoutes(new IntegrationNormalizer());
        camel.addRoutes(new WireTapNewClients());
        camel.addRoutes(new ConstructionDecision());

        //camel.addRoutes(new IntegrationRoute());
        camel.start();
        Thread.sleep(30000);
        camel.stop();


       // clientRequestService.createNewRequest(request);
    }
}
