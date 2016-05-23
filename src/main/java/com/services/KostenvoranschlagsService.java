package com.services;

import com.database.employeeDB.model.SpecializationType;
import com.database.projectDB.model.Offer;
import com.database.projectDB.model.Request;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by Johannes on 19.05.2016.
 */

public class KostenvoranschlagsService {
    private static final Logger LOG = LoggerFactory.getLogger(KostenvoranschlagsService.class);

    @EndpointInject(uri="activemq:com.kostenvoranschlag")
    ProducerTemplate producer;

    public KostenvoranschlagsService() throws Exception {

    }

    public Offer makeForTiefbau(Exchange exchange) throws Exception {
        LOG.debug("we are making a Kostenvoranschlag for the Tiefbau!");

        Request request = exchange.getOut().getBody(Request.class);

        //producer.sendBody("<hello>world!</hello>");

        Offer offer = new Offer();
        offer.setDescription(request.getDescription());
        offer.setClientId(request.getClientId());
        offer.setCreatedAt(new Date());
        offer.setEstimated_days(request.getSquaremeters().intValue() * 10);
        offer.setFinishDate(new Date(new Date().getTime() + offer.getEstimated_days() * 1000 * 60 * 60 * 24 * 5));
        offer.setStartDate(new Date(new Date().getTime() +  1000 * 60 * 60 * 24 * 5));
        if(request.getSpecializationType().equals(SpecializationType.HOCHBAU)){
            offer.setManHourCosts(request.getSquaremeters().intValue() * 200);
            offer.setMaterialcosts(request.getSquaremeters().intValue() * 4);
        } else {
            offer.setManHourCosts(request.getSquaremeters().intValue() * 5);
            offer.setMaterialcosts(request.getSquaremeters().intValue() * 2);
        }

        return offer;
    }

    public Offer makeForHochbau(Exchange exchange) throws Exception {
        LOG.debug("we are making a Kostenvoranschlag for the Hochbau!");

        return null;
    }
}