package com.database.employeeDB.repository;

import com.database.employeeDB.model.ProjectManager;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by rudolfplettenberg on 05.05.16.
 */
public interface ProjectManagerRepository extends CrudRepository<ProjectManager, Long> {
    @Query("SELECT p FROM ProjectManager p WHERE LOWER(p.specialization) = LOWER(:specialization)")
    public List<ProjectManager> findBySpecialization(@Param("specialization") String specialization);

    @Query("SELECT p FROM ProjectManager p WHERE LOWER(p.firstName) = LOWER(:firstName)")
    public List<ProjectManager> findByFirstName(@Param("firstName") String firstName);
    @Query("SELECT p FROM ProjectManager p WHERE LOWER(p.lastName) = LOWER(:lastName)")
    public List<ProjectManager> findByLastName(@Param("lastName") String lastName);
    @Query("SELECT p FROM ProjectManager p WHERE LOWER(p.firstName) = LOWER(:firstName) AND LOWER(p.lastName) = LOWER(:lastName)")
    public List<ProjectManager> findByFirstNameLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
