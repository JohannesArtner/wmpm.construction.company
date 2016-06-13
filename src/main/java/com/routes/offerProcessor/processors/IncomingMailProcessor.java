package com.routes.offerProcessor.processors;

import com.routes.offerProcessor.model.OfferAcceptionModel;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

/**
 * Created by mionisation on 5/24/16.
 */
public class IncomingMailProcessor implements Processor {
    static Logger logger = Logger.getLogger(IncomingMailProcessor.class.getName());

    @Override
    public void process(Exchange exchange) throws Exception {
        String fromAdress = (String)exchange.getIn().getHeaders().get("from");
        /*Log contents of email
        logger.info("-------------------------------");
        logger.info("Incoming Offer Mail:");
        logger.info("Header: "+exchange.getIn().getHeaders().toString());
        logger.info("Subject: "+exchange.getIn().getHeaders().get("subject").toString());
        logger.info("Body: "+exchange.getIn().getBody().toString());
        logger.info("From: "+fromAdress);
        logger.info("-------------------------------");
        */
        /**
         * Set java object to store attributes
         * Example email format:
         * 1 - ID of offer in db
         * OK - response: is 'OK' if accepted, NOT if not
         * "Please include a swimming pool" - responseNotes: some other line with info
         */
        String[] oaArr = exchange.getIn().getBody().toString().split("\\n");
        OfferAcceptionModel oa = new OfferAcceptionModel();
        oa.setProjectId(oaArr[0]);
        oa.setResponse(oaArr[1]);
        oa.setResponseNotes(oaArr[2]);
        oa.setCustomerName(fromAdress.split("<")[0].trim());
        oa.setCustomerMail(fromAdress.split("<")[1].replace(">", ""));
        logger.info("------------BEGIN-------------------");
        logger.info("OfferAcceptionModel for this mail");
        logger.info("Project ID: " + oa.getProjectId());
        logger.info("Response: " + oa.getResponse());
        logger.info("Notes: " + oa.getResponseNotes());
        logger.info("Customer mail: " + oa.getCustomerMail());
        logger.info("Customer name: " + oa.getCustomerName());
        logger.info("------------END-------------------");

        exchange.getOut().setBody(oa);
    }
}
