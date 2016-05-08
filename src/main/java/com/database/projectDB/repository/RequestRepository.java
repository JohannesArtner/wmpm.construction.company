package com.database.projectDB.repository;

import com.database.employeeDB.model.SpecializationType;
import com.database.projectDB.model.Request;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by rudolfplettenberg on 07.05.16.
 */
public interface RequestRepository extends MongoRepository<Request, String> {

    public List<Request> findByClientId(long clientId);
    public List<Request> findBySpecializationType(SpecializationType specializationType);

}
