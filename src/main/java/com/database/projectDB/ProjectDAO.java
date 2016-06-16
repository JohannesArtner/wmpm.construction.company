package com.database.projectDB;

import com.database.employeeDB.model.SpecializationType;
import com.database.projectDB.model.Project;
import com.database.projectDB.model.ProjectStatus;
import com.database.projectDB.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rudolfplettenberg on 08.05.16.
 */
@Service("projectDAO")
public class ProjectDAO {
    @Autowired
    ProjectRepository projectRepository;

    public ProjectDAO() {
    }

    public Project save(Project project){
        return projectRepository.save(project);
    }

    public Iterable<Project> save(Iterable<Project> projects){
        return projectRepository.save(projects);
    }

    public Project findById(String id){
        return projectRepository.findOne(id);
    }

    public List<Project> findAll(){
        return projectRepository.findAll();
    }

    public List<Project> findByClient(long clientId){
        return projectRepository.findByClientId(clientId);
    }

    public List<Project> findByProjectManagerId(long projectManagerId){
        return projectRepository.findByProjectManagerId(projectManagerId);
    }

    public List<Project> findByProjectStatus(ProjectStatus projectStatus){
        return projectRepository.findByProjectStatus(projectStatus);
    }

    public boolean exists(String id){
        return projectRepository.exists(id);
    }

    public long count(){
        return projectRepository.count();
    }
}
