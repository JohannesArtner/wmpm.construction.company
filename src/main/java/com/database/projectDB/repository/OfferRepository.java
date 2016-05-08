package com.database.projectDB.repository;

import com.database.projectDB.model.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by rudolfplettenberg on 08.05.16.
 */
public interface OfferRepository extends MongoRepository<Offer, String> {

    public List<Offer> findByClientId(long clientId);
    public List<Offer> findBySalesmanId(long salesmanId);

}
