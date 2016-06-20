package com.routes.requestInput.processor;

import com.database.clientDB.model.Client;
import com.database.projectDB.model.Request;
import com.routes.requestInput.model.MailInputModel;
import com.routes.requestInput.routes.RouteRequestFormInputToNormalizer;
import com.routes.requestInput.model.NormalizedInput;
import com.routes.requestInput.model.RestFormInputModel;
import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * Rudolf & Wailzer
 * Normalizes the two possible inputs (forms and Input)
 */
@Service
public class RequestNormalizer {
    static Logger logger = Logger.getLogger(RequestNormalizer.class.getName());


    public void formToRequest(Exchange exchange) {
        logger.info("Normalizing Form Data: "+exchange.getIn().getBody().toString());
        RestFormInputModel restFormInputModel = exchange.getIn().getBody(RestFormInputModel.class);


        Request request = new Request();
        request.setDateFrom(restFormInputModel.getDateFrom());
        request.setDateTo(restFormInputModel.getDateTo());
        request.setDescription(restFormInputModel.getDescription());
        request.setSpecializationType(restFormInputModel.getSpecializationType());
        request.setSquaremeters(restFormInputModel.getSquaremeter());

        Client client = new Client();
        client.setClientFirstname(restFormInputModel.getClientFirstname());
        client.setClientLastname(restFormInputModel.getClientLastname());
        client.setCompanyName(restFormInputModel.getCompanyName());
        client.setEmail(restFormInputModel.getEmail());
        client.setAddress(restFormInputModel.getAddress());
        client.setTelephone(restFormInputModel.getTelephone());

        logger.debug("Created Request: "+request.toString());
        logger.debug("Created Client: "+client.toString());

        NormalizedInput body = new NormalizedInput(client,request);
        exchange.getOut().setBody(body);
        exchange.getOut().setHeaders(exchange.getIn().getHeaders());
        exchange.getOut().setAttachments(exchange.getIn().getAttachments());
    }

    public void emailToRequest(Exchange exchange){
        logger.info("Normalizing Form Email Body: " + exchange.getIn().getBody().toString());
        logger.info("Normalizing Form Email Header: " + exchange.getIn().getHeaders().toString());

        MailInputModel mIM = new MailInputModel(exchange);

        Request request = new Request();
        request.setDateFrom(mIM.getDateFrom());
        request.setDateTo(mIM.getDateTo());
        request.setDescription(mIM.getDescription());
        request.setSpecializationType(mIM.getSpecializationType());
        request.setSquaremeters(mIM.getSquaremeter());

        Client client = new Client();
        client.setClientFirstname(mIM.getClientFirstname());
        client.setClientLastname(mIM.getClientLastname());
        client.setCompanyName(mIM.getCompanyName());
        client.setEmail(mIM.getEmail());
        client.setAddress(mIM.getAddress());
        client.setTelephone(mIM.getTelephone());

        logger.debug("Created Request: "+request.toString());
        logger.debug("Created Client: "+client.toString());

        //logger.info("Normalizing Form Data: " + exchange.getIn().getBody().toString());
        logger.info("Debug message body for normalisation: "+exchange.getIn().getBody().toString());

        NormalizedInput body = new NormalizedInput(client,request);

        exchange.getOut().setBody(body);
        exchange.getOut().setHeaders(exchange.getIn().getHeaders());
        exchange.getOut().setAttachments(exchange.getIn().getAttachments());
    }



}
