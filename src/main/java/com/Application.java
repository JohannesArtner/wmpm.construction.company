package com;
import com.database.employeeDB.EmployeeDAO;
import com.database.employeeDB.model.Employee;
import com.database.employeeDB.model.ProjectManager;
import com.database.employeeDB.model.SpecializationType;
import com.database.projectDB.RequestDAO;
import com.database.projectDB.model.Request;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.services.RequestService;
import org.apache.camel.*;

import java.util.Date;

/**
 * Created by mionisation on 4/27/16.
 */

@SpringBootApplication
public class Application implements CommandLineRunner {

    static Logger logger = Logger.getLogger(Application.class.getName());


    @Autowired
    private RequestDAO requestDAO;
    @Autowired
    private EmployeeDAO employeeDAO;

    public static void main(String[] args) {
        logger.info("----------STARTING APPLICATION----------");
        SpringApplication.run(Application.class);
    }

    @Autowired
    private CamelContext camelContext;

    @Override
    public void run(String ... args) throws Exception {

        /*
        logger.info("MAKING NEW REQUEST");
        Request r = new Request();
        r.setDateFrom(new Date());
        r.setDateTo(new Date());
        r.setSpecializationType(SpecializationType.HOCHBAU);
        r.setLocation("Location");
        r.setSquaremeters(400d);
        r.setDescription("Description");

        requestDAO.save(r);
        logger.info("NEW REQUEST SAVED");
        */


        try {
            ProducerTemplate template = camelContext.createProducerTemplate();
        } finally {

            while(true){

            }
            //Not stopping Camelcontext so that outes stay alive and Rest can be tested
            //camelContext.stop();
        }

    }
}
