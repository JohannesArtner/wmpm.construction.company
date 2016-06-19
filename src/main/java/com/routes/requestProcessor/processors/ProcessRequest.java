package com.routes.requestProcessor.processors;

import com.database.employeeDB.model.SpecializationType;
import com.database.projectDB.model.Request;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Johannes on 19.05.2016.
 */
@Service
public class ProcessRequest implements Processor {

    private static final Logger LOG = LoggerFactory.getLogger(ProcessRequest.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        Request request = (Request) exchange.getIn().getBody();
        request.setRead(true);

        if(request.getSpecializationType().equals(SpecializationType.HOCHBAU)) {
            exchange.getOut().setHeader("constructiontype", "hochbau");
        } else if(request.getSpecializationType().equals(SpecializationType.TIEFBAU)){
            exchange.getOut().setHeader("constructiontype", "tiefbau");
        }

        exchange.getOut().setBody(request);
    }

}