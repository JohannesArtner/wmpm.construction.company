package com.routes.requestProcessor.processors;

import com.database.clientDB.ClientDAO;
import com.database.projectDB.OfferDAO;
import com.database.projectDB.RequestDAO;
import com.database.projectDB.model.Offer;
import com.database.projectDB.model.Request;
import com.routes.requestInput.model.NormalizedInput;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Persists Offers in the database
 */
@Service
public class DatabaseOfferProcessor implements Processor {
    static Logger logger = Logger.getLogger(Processor.class.getName());

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("Saving new Offer: "+exchange.getIn().toString());
        Offer offer = exchange.getIn().getBody(Offer.class);

        exchange.getIn().setBody("INSERT INTO " +
                "Offer (salesmanId, createdAt, clientId, description, sent, startDate, finishDate, estimated_days, manhours, manHourCosts, materialCosts) " +
                "VALUES('"
                + exchange.getIn().getBody(String.class)
                + "'");

        logger.info("Saved the offer successfully!");
    }
}
