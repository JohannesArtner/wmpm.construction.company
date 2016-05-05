package com.database.clientDB.repository;

import com.database.clientDB.model.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by rudolfplettenberg on 05.05.16.
 */
public interface ClientRepository extends CrudRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE LOWER(c.companyName) = LOWER(:companyName)")
    public List<Client> findByCompanyName(@Param("companyName") String companyName);
    @Query("SELECT c FROM Client c WHERE LOWER(c.email) = LOWER(:email)")
    public List<Client> findByEmail(@Param("email") String email);

}
