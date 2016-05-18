package com;
import com.database.projectDB.model.Request;
import com.routes.IntegrationNormalizer;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.services.ClientRequestService;
import org.apache.camel.*;

import java.util.Date;

/**
 * Created by mionisation on 4/27/16.
 */

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ClientRequestService clientRequestService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Autowired
    private CamelContext camelContext;

    @Override
    public void run(String ... args){
        Request request = new Request();

        //clientRequestService.createNewRequest(request);



    }
}
