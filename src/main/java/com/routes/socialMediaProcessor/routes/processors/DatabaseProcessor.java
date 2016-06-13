package com.routes.socialMediaProcessor.routes.processors;

import com.routes.offerProcessor.model.OfferAcceptionModel;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

public class DatabaseProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String fromAdress = (String)exchange.getIn().getHeaders().get("from");
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


        exchange.getOut().setBody(oa);
    }
}
