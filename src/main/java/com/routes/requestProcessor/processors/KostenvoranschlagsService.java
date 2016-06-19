package com.routes.requestProcessor.processors;

import com.database.projectDB.OfferDAO;
import com.database.projectDB.model.Offer;
import com.database.projectDB.model.Request;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Johannes on 19.05.2016.
 */

@Service
public class KostenvoranschlagsService {
    private static final Logger LOG = LoggerFactory.getLogger(KostenvoranschlagsService.class);

    @Autowired
    private OfferDAO offerDAO;

    //@EndpointInject(uri="activemq:com.kostenvoranschlag")
    ProducerTemplate producer;

    public KostenvoranschlagsService() throws Exception {

    }

    public Offer makeForTiefbau(Request request) throws Exception {
        LOG.debug("we are making a Kostenvoranschlag for the Tiefbau!");

        Offer offer = makeOffer(request);
        offer.setDescription(offer.getDescription() + " for Tiefbau!!");

        offerDAO.saveJPA(offer);

        return offer;
    }

    private Offer makeOffer(Request request) {
        Offer offer = new Offer();
        offer.setRequest(request);
        offer.setDescription(request.getDescription());
        offer.setClientId(request.getClientId());
        offer.setCreatedAt(new Date());
        offer.setEstimated_days(request.getSquaremeters().intValue() * 10);
        offer.setFinishDate(new Date(new Date().getTime() + offer.getEstimated_days() * 1000 * 60 * 60 * 24 * 5));
        offer.setStartDate(new Date(new Date().getTime() +  1000 * 60 * 60 * 24 * 5));
        offer.setManHourCosts(request.getSquaremeters().intValue() * 200);
        offer.setMaterialcosts(request.getSquaremeters().intValue() * 4);
        offer.setEmailCostumer(request.getEmailCustomer());

        return offer;
    }

    public Offer makeForHochbau(Request request) throws Exception {
        LOG.debug("we are making a Kostenvoranschlag for the Hochbau!");

        Offer offer = makeOffer(request);
        offer.setDescription(offer.getDescription() + " for Hochbau!!");
        offer.setManHourCosts(request.getSquaremeters().intValue() * 5);
        offer.setMaterialcosts(request.getSquaremeters().intValue() * 2);

        offerDAO.saveJPA(offer);

        return offer;
    }


}