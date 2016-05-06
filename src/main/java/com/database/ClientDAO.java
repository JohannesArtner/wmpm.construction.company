package com.database;

import com.database.clientDB.model.Client;
import com.database.clientDB.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This Class provides services for the Client Database
 *
 * Since it uses @Autowired you need to Autowire this Class as well when using it.
 * If you create thi Class with new() all functions will not work
 *
 * Created by rudolfplettenberg on 05.05.16.
 */
@Service
public class ClientDAO {
    @Autowired
    ClientRepository clientRepository;

    public ClientDAO() {
    }

    /**
     * Find all Clients in the Database
     * @return Iterable<Client> with all Clients in Database. Returns empty Iterable if no Entities in Database
     */
    public Iterable<Client> findAll(){
        return clientRepository.findAll();
    }

    /**
     * Find all Clients with the provided Ids
     * @return Iterable<Client> with all Clients with the provided IDs. Returns empty Iterable if no Clients with maching IDs were found
     */
    public Iterable<Client> findAll(Iterable<Long> ids){
        return clientRepository.findAll(ids);
    }

    /**
     *Find one Client by id (long)
     * @param id id oft the Client you want to load
     * @return returns Client with provided id or NULL if no client was found
     */
    public Client findById(long id){
        return clientRepository.findOne(id);
    }

    /**
     * Find Company by specified Name
     * @param companyName Name of company you want to search for. CASE-INSENSITIVE
     * @return List of Companies that match the provided Name. List is empty if no matches were found
     */
    public List<Client> findByCompanyName(String companyName){
        return clientRepository.findByCompanyName(companyName);
    }
    /**
     * Find Company by specified Name
     * @param email Email of company you want to search for. CASE-INSENSITIVE
     * @return List of Companies that match the provided email. List is empty if no matches were found
     */
    public List<Client> findByEmail(String email){
        return clientRepository.findByEmail(email);
    }

    /**
     * Returns whether a Client with the given id exists.
     * @param id id of the Client you want to check for
     * @return true if Client with the given id exists, false otherwise
     */
    public boolean exists(long id){
        return clientRepository.exists(id);
    }

    /** Returns the number of Clients available.
     *
     * @return Returns the number of Clients available.
     */
    public long count(){
        return clientRepository.count();
    }

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the entity instance completely.
     * @param client Client you want to Save
     * @return Client with ID
     */
    public Client save(Client client){
        return clientRepository.save(client);
    }

    /**
     * Saves all given entities.
     * @param clients Iterable of Clients you want to Save
     * @return the saved clients (with Ids)
     */
    public Iterable<Client> save(Iterable<Client> clients){
        return clientRepository.save(clients);
    }




}
