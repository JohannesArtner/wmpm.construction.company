package com.routes.requestInput.processor;

import com.database.clientDB.model.Client;
import com.database.projectDB.model.Request;
import com.routes.requestInput.routes.RouteRestFormInput;
import com.routes.requestInput.model.NormalizedInput;
import com.routes.requestInput.model.RestFormInputModel;
import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Normalizes the two possible inputs (forms and Input)
 */
@Service
public class RequestNormalizer {
    static Logger logger = Logger.getLogger(RouteRestFormInput.class.getName());


    public void formToRequest(Exchange exchange) {
        logger.info("Normalizing Form Data: "+exchange.getIn().getBody().toString());
        RestFormInputModel restFormInputModel = exchange.getIn().getBody(RestFormInputModel.class);


        Request request = new Request();
        request.setDateFrom(restFormInputModel.getDateFrom());
        request.setDateTo(restFormInputModel.getDateTo());
        request.setDescription(restFormInputModel.getDescription());
        request.setSpecializationType(restFormInputModel.getSpecializationType());
        request.setSquaremeter(restFormInputModel.getSquaremeter());

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

        Map<String,Object> body = new HashMap();
        body.put("client",null);
        body.put("request",null);

        exchange.getOut().setBody(body);
        exchange.getOut().setHeaders(exchange.getIn().getHeaders());
        exchange.getOut().setAttachments(exchange.getIn().getAttachments());
    }



}
