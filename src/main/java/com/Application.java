package com;
import com.model.ClientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.services.ClientRequestService;

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

    @Override
    public void run(String ... args){
        ClientRequest request = new ClientRequest("Max", "Mustermann", "max@muster.at", "Musterstraße 18/2, 1010 Wien", "Ich will ein neues Haus bauen!", new Date(), new Date());

        clientRequestService.createNewRequest(request);
    }
}
