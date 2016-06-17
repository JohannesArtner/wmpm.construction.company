package com.routes.requestProcessor.routes;

import com.database.employeeDB.model.SpecializationType;
import com.database.projectDB.model.Offer;
import com.database.projectDB.model.Request;
import com.routes.requestProcessor.processors.ProcessRequest;
import com.services.KostenvoranschlagsService;
import com.services.RequestService;
import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.PollingConsumer;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * Created by Johannes
 */

@Component
public class PollRequests extends RouteBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(ProcessRequest.class);

    @Autowired
    RequestService requestService;

    @Autowired
    CamelContext context;

    @Autowired
    ProcessRequest processor;

    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("camel");
        EntityManager entityManager = factory.createEntityManager();

        Request request = new Request();
        request.setDescription("This is a dummy request!");
        request.setDateFrom(new Date());
        request.setDateTo(new Date());
        request.setLocation("Vienna");
        request.setSpecializationType(SpecializationType.HOCHBAU);
        request.setSquaremeters(500.0);
        request.setRead(false);
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(request);
            entityManager.getTransaction().commit();
            log.info("new dummy request was saved!");
        }catch(Exception ex){
            log.error("couldn't create request with jpa!", ex);
        } finally{
            log.info("done with saving a dummy request!");
        }
    }

    @Override
    public void configure() throws Exception {
        from("jpa://com.database.projectDB.model.Request?consumer.query=select o from Request o").bean(KostenvoranschlagsService.class, "makeForHochbau")
                .to("direct:processRequest");
    }
}
