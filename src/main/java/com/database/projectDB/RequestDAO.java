package com.database.projectDB;

import com.database.employeeDB.model.SpecializationType;
import com.database.projectDB.model.Request;
import com.database.projectDB.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *Created by rudolfplettenberg on 08.05.16.
 */
@Service
public class RequestDAO {
    @Autowired
    RequestRepository requestRepository;

    public RequestDAO() {
    }

    public Request save(Request request){
        return requestRepository.save(request);
    }

    public Iterable<Request> save(Iterable<Request> requests){
        return requestRepository.save(requests);
    }

    public Request findById(String id){
        return requestRepository.findOne(id);
    }

    public List<Request> findAll(){
        return requestRepository.findAll();
    }

    public List<Request> findByClient(long clientId){
        return requestRepository.findByClientId(clientId);
    }

    public List<Request> findBySpecialization(SpecializationType specializationType){
        return requestRepository.findBySpecializationType(specializationType);
    }

    public boolean exists(String id){
        return requestRepository.exists(id);
    }

    public long count(){
        return requestRepository.count();
    }
}
