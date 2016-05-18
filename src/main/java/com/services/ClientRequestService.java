package com.services;

import com.database.clientDB.ClientDAO;
import com.database.projectDB.ProjectDAO;
import com.database.projectDB.RequestDAO;
import com.database.projectDB.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by Johannes on 04.05.2016.
 */
@Service
public class ClientRequestService {

    @Autowired
    private RequestDAO requestDAO;

    @PostConstruct
    public void init() {
        System.out.println("service from @service");
    }

    public void createNewRequest(Request clientRequest){
        requestDAO.save(clientRequest);
    }

}
