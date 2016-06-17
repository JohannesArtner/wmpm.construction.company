package com.database.projectDB;

import com.database.projectDB.model.Offer;
import com.database.projectDB.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by rudolfplettenberg on 08.05.16.
 */
@Service
public class OfferDAO {

    @Autowired
    OfferRepository offerRepository;

    public OfferDAO() {
    }

    public Offer save(Offer offer){
        return offerRepository.save(offer);
    }

    public Iterable<Offer> save(Iterable<Offer> offers){
        return offerRepository.save(offers);
    }

    public Offer findById(String id){
        return offerRepository.findOne(id);
    }

    public List<Offer> findAll(){
        return offerRepository.findAll();
    }

    public List<Offer> findByClient(long clientId){
        return offerRepository.findByClientId(clientId);
    }

    public List<Offer> findBySalesman(long salesmanId){
        return offerRepository.findBySalesmanId(salesmanId);
    }

    public boolean exists(String id){
        return offerRepository.exists(id);
    }

    public long count(){
        return offerRepository.count();
    }

    public void saveJPA(Offer offer) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("camel");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(offer);
        entityManager.getTransaction().commit();

    }
}
