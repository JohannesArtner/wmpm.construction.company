package com.routes.requestProcessor.processors;

import com.database.projectDB.model.Offer;
import com.database.projectDB.model.Request;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Johannes on 19.05.2016.
 */
@Service
public class ProcessOffer implements Processor {

    private static final Logger LOG = LoggerFactory.getLogger(ProcessOffer.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        Offer offer = (Offer) exchange.getIn().getBody();
        offer.setRead(true);

        exchange.getOut().setHeader(Exchange.FILE_NAME, "offer" + offer.getId());

        exchange.getOut().setBody(offer);
    }

}