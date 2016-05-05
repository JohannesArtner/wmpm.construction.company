package com.database.employeeDB.repository;

import com.database.employeeDB.model.Salesman;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by rudolfplettenberg on 05.05.16.
 */
public interface SalesmanRepository extends CrudRepository<Salesman, Long> {
    @Query("SELECT s FROM Salesman s WHERE LOWER(s.specialization) = LOWER(:specialization)")
    public List<Salesman> findBySpecialization(@Param("specialization") String specialization);

    @Query("SELECT s FROM Salesman s WHERE LOWER(s.firstName) = LOWER(:firstName)")
    public List<Salesman> findByFirstName(@Param("firstName") String firstName);
    @Query("SELECT s FROM Salesman s WHERE LOWER(s.lastName) = LOWER(:lastName)")
    public List<Salesman> findByLastName(@Param("lastName") String lastName);
    @Query("SELECT s FROM Salesman s WHERE LOWER(s.firstName) = LOWER(:firstName) AND LOWER(s.lastName) = LOWER(:lastName)")
    public List<Salesman> findByFirstNameLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

}
