package com.routes.offerProcessor.processors;

import com.database.projectDB.ProjectDAO;
import com.routes.offerProcessor.model.OfferAcceptionModel;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mionisation on 5/24/16.
 */
public class RejectedOfferPersistor implements Processor {
    static Logger logger = Logger.getLogger(RejectedOfferPersistor.class.getName());

    @Autowired
    ProjectDAO projectDAO;
    //save to this

    @Override
    public void process(Exchange exchange) throws Exception {
        OfferAcceptionModel oa = (OfferAcceptionModel)exchange.getIn().getBody();
        logger.info("Heureka! Customer name: " + oa.getCustomerName());
    }
}
