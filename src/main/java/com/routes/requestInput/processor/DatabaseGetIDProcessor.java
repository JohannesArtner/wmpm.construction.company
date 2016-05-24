package com.routes.requestInput.processor;

import com.database.clientDB.ClientDAO;
import com.database.clientDB.model.Client;
import com.database.projectDB.RequestDAO;
import com.database.projectDB.model.Request;
import com.routes.requestInput.model.NormalizedInput;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Wailzer
 *
 * Receive ID from DB if possible
 */
@Service
public class DatabaseGetIDProcessor implements Processor {
    static Logger logger = Logger.getLogger(Processor.class.getName());

    @Autowired
    RequestDAO requestDAO;
    @Autowired
    ClientDAO clientDAO;

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("Checking the : "+exchange.getIn().toString());
        NormalizedInput body = exchange.getIn().getBody(NormalizedInput.class);
        logger.info("Headers: "+exchange.getIn().getHeaders().toString());
        logger.info("Body: "+exchange.getIn().getBody().toString());
        logger.info("extracted Body: "+body.toString());

        Client find = new Client();

        find = clientDAO.findById(body.getClient().getId());
        if(find != null) {
            logger.info("Found ID: " + exchange.getIn().getHeaders().toString());
            exchange.getOut().setBody(exchange.getIn().toString() + " ALREADY_EXISTS ID:" + find.getId());
        }
        if(find == null){
            List<Client> findList = clientDAO.findByEmail(body.getClient().getEmail());
            logger.info("Found ID: "+exchange.getIn().getHeaders().toString());
            exchange.getOut().setBody(exchange.getIn().toString() + " ALREADY_EXISTS + ID:" + findList.get(0).getId());

        }
        else if(find == null){
            List<Client> findList = clientDAO.findByCompanyName(body.getClient().getCompanyName());
            logger.info("Found ID: "+exchange.getIn().getHeaders().toString());
            exchange.getOut().setBody(exchange.getIn().toString() + " ALREADY_EXISTS + ID:" + findList.get(0).getId());
        }
        else{
            //if(clientDAO == null){
                logger.info("Nothing found -save all");
            exchange.getOut().setBody(exchange.getIn().toString() + " CREATE_NEW");
        }

        exchange.getOut().setHeaders(exchange.getIn().getHeaders());
        //exchange.getOut().setAttachments(exchange.getIn().getAttachments());

    }
}
