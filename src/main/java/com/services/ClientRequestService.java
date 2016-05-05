package com.services;

import com.model.ClientRequest;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by Johannes on 04.05.2016.
 */
@Service
public class ClientRequestService {

    @PostConstruct
    public void init() {
        System.out.println("service from @service");
    }

    public void createNewRequest(ClientRequest clientRequest){
        //Do some fancy jpa stuff here!
    }

}
