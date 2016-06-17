package com.routes.requestProcessor.routes;

import com.database.employeeDB.model.SpecializationType;
import com.database.projectDB.model.Offer;
import com.database.projectDB.model.Request;
import com.routes.requestProcessor.processors.ProcessRequest;
import com.services.KostenvoranschlagsService;
import com.services.RequestService;
import org.apache.camel.*;
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

    @Override
    public void configure() throws Exception {
        from("jpa://com.database.projectDB.model.Request?persistenceUnit=camel&consumer.query=select o from Request o").
                to("direct:processRequest");
    }
}
