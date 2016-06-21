package com.routes.offerProcessor.processors;

import com.database.employeeDB.EmployeeDAO;
import com.database.employeeDB.model.ProjectManager;
import com.database.projectDB.model.Offer;
import com.routes.offerProcessor.model.OfferAcceptionModel;
import org.apache.camel.Exchange;
import com.database.projectDB.OfferDAO;
import org.apache.camel.Processor;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mionisation on 5/21/16.
 */
public class EnrichProjectAggregationStrategy implements AggregationStrategy {
    static Logger logger = Logger.getLogger(EnrichProjectAggregationStrategy.class.getName());

    @Autowired
    OfferDAO offerDAO;
    @Autowired
    EmployeeDAO employeeDAO;


    public Exchange aggregate(Exchange original, Exchange resource) {
        /**
         * Append the project managers to the original message
         */
        logger.info("ENRICH CONTENT:");
        logger.info("original in: " + original.getIn().toString());
        logger.info("resource in: " + resource.getIn().toString());

        OfferAcceptionModel oa = (OfferAcceptionModel)original.getIn().getBody();
        oa.setProjectManagerId(resource.getIn().getBody().toString());


        if (original.getPattern().isOutCapable()) {
            original.getOut().setBody(oa);
        } else {
            original.getIn().setBody(oa);
        }
        return original;
    }

}