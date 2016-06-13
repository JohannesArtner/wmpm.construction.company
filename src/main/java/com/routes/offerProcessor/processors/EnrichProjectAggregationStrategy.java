package com.routes.offerProcessor.processors;

import com.database.employeeDB.EmployeeDAO;
import com.database.employeeDB.model.ProjectManager;
import com.database.projectDB.model.Offer;
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
/*        logger.info("HELLO TEST");
        logger.info("original in: " + original.getIn().toString());
        logger.info("original out: " + original.getOut().toString());
        logger.info("resource in: " + resource.getIn().toString());
        logger.info("resource out: " + resource.getIn().toString());


        String originalMessage = original.getIn().getBody().toString();

        String client = original.getIn().getHeaders().toString();
        String projectId = originalMessage.split("#")[1];
        //TODO: might need adjustements depending on test data?
        //TODO: adjust db models to include:
        // project ID for project manager// or find by specialization
        // boolean accepted for projects
        ProjectManager projectManager = employeeDAO.findProjectManagerById(Long.parseLong(projectId));
        Offer offer = offerDAO.findById(projectId);


        Object resourceResponse = resource.getIn().getBody();
        //Combine original body and resource response
        String mergeResult = projectManager.toString() + " # " + offer.toString();
        if (original.getPattern().isOutCapable()) {
            original.getOut().setBody(mergeResult);
        } else {
            original.getIn().setBody(mergeResult);
        }*/
        return original;
    }

}