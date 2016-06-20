package com.routes.socialMediaProcessor.routes.processors;

import com.routes.offerProcessor.model.OfferAcceptionModel;
import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TwitterProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        OfferAcceptionModel oa = (OfferAcceptionModel)exchange.getIn().getBody();

        /**
         * Set java object to store attributes
         * Example email format:
         * 1 - ID of offer in db
         * OK - response: is 'OK' if accepted, NOT if not
         * "Please include a swimming pool" - responseNotes: some other line with info
         */

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        String out = dateFormat.format(date).toString().concat("; PROJECT" + oa.getProjectId() + " TESTING BY MAX: " + oa.getCustomerName());

        exchange.getOut().setBody(out);

    }
}
