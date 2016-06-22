package com.routes.offerProcessor.processors;

import com.database.projectDB.ProjectDAO;
import com.database.projectDB.model.Project;
import com.database.projectDB.model.ProjectStatus;
import com.routes.offerProcessor.model.OfferAcceptionModel;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by mionisation on 5/24/16.
 */
@Component("acceptedOfferPersistor")
public class AcceptedOfferPersistor implements Processor {
    static Logger logger = Logger.getLogger(AcceptedOfferPersistor.class.getName());

    @Autowired
    ProjectDAO projectDAO;
    @Override
    public void process(Exchange exchange) throws Exception {
        OfferAcceptionModel oa = (OfferAcceptionModel)exchange.getIn().getBody();
        //set project status to rejected and persist
        logger.info("Persisting accepted offer with project id: " + oa.getProjectId().trim());
        Project p = projectDAO.findById(oa.getProjectId().trim());
        p.setProjectStatus(ProjectStatus.IN_PREPARATION);
        projectDAO.save(p);
        File lock = new File("/testdata/projectManager.txt.camelLock");
        if(lock.exists()) {
            logger.info("File does not exist.");
        }
        if(lock.canRead()) {
            logger.info("File can not be read.");
        }
        if(lock.canWrite()) {
            logger.info("File can not write.");
        }
        if(lock.delete()){
            logger.info(lock.getName() + " is deleted!");
        }else{
            logger.info("Delete operation is failed.");
        }
    }
}
