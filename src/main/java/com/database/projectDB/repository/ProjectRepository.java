package com.database.projectDB.repository;

import com.database.projectDB.model.Project;
import com.database.projectDB.model.ProjectStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rudolfplettenberg on 08.05.16.
 */
public interface ProjectRepository extends MongoRepository<Project, String> {

    public List<Project> findByClientId(long clientId);
    public List<Project> findByProjectManagerId(long projectManagerId);
    public List<Project> findByProjectStatus(ProjectStatus projectStatus);
}
