package com.routes.offerProcessor.processors;

import com.database.projectDB.ProjectDAO;
import com.database.projectDB.model.Offer;
import com.database.projectDB.model.Project;
import com.database.projectDB.model.ProjectStatus;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by mionisation on 6/16/16.
 */
@Component("testDataProcessor")
public class TestDataProcessor implements Processor {
    @Autowired
    ProjectDAO projectDAO;

    static Logger logger = Logger.getLogger(RejectedOfferPersistor.class.getName());

    @Override
    public void process(Exchange exchange) throws Exception {
        //create test data

        Project p = new Project();
        p.setId("1");
        p.setProjectStatus(ProjectStatus.IN_PREPARATION);
        if(projectDAO == null) {
            logger.error("PROJECT DAO NOT INJECTED");
        }
        projectDAO.save(p);
    }
}
