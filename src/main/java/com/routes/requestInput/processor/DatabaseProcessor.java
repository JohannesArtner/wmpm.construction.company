package com.routes.requestInput.processor;

import com.database.clientDB.ClientDAO;
import com.database.clientDB.model.Client;
import com.database.projectDB.RequestDAO;
import com.database.projectDB.model.Request;
import com.routes.requestInput.model.NormalizedInput;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Persists Clients and Requests in the database
 */
@Service
public class DatabaseProcessor implements Processor {
    static Logger logger = Logger.getLogger(Processor.class.getName());

    @Autowired
    RequestDAO requestDAO;
    @Autowired
    ClientDAO clientDAO;

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("Saving new Request: "+exchange.getIn().toString());
        NormalizedInput body = exchange.getIn().getBody(NormalizedInput.class);
        logger.info("Headers: "+exchange.getIn().getHeaders().toString());
        logger.info("Body: "+exchange.getIn().getBody().toString());
        logger.info("extracted Body: "+body.toString());

        if(clientDAO == null){
            logger.info("!!! SERVICE IS NULL !!!");
        }

        Client createdClient = clientDAO.save(body.getClient());
        long id = createdClient.getId();

        logger.info("New Client created: "+createdClient.toString());
        Request r = body.getRequest();
        r.setClientId(id);
        logger.info("New Request created: "+r.toString());
        requestDAO.save(r);
        logger.info("Save complete");


        List<Request> requests = requestDAO.findAll();
        logger.info("-------------------------------");
        logger.info("Requests in DB: "+requests.size());
        for(Request rr: requests){
            logger.info(rr.toString());
        }

    }
}
