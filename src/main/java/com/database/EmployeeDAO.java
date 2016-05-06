package com.database;

import com.database.employeeDB.model.Employee;
import com.database.employeeDB.model.ProjectManager;
import com.database.employeeDB.model.Salesman;
import com.database.employeeDB.repository.ProjectManagerRepository;
import com.database.employeeDB.repository.SalesmanRepository;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class provides services for the Employee Database
 *
 * Since it uses @Autowired you need to Autowire this Class as well when using it.
 * If you create thi Class with new() all functions will not work
 *
 * Created by rudolfplettenberg on 05.05.16.
 */
@Service
public class EmployeeDAO {
    @Autowired
    SalesmanRepository salesmanRepository;
    @Autowired
    ProjectManagerRepository projectManagerRepository;

    public EmployeeDAO() {
    }

    //
    //Employee Services
    //

    /**
     * Saves an Employee in the DB
     * @param employee Employee you want to sabe
     * @return returns the persisted Employee Entity.
     */
    public Employee save(Employee employee){
        if(employee instanceof Salesman){
            return salesmanRepository.save((Salesman) employee);
        }
        if(employee instanceof ProjectManager){
            return projectManagerRepository.save((ProjectManager) employee);
        }
        return null;
    }
    /**
     * Loads all available Employees
     * @return returns a List of Employees and its subClasses. Empty if none were found
     */
    public List<Employee> findAllEmployees(){
        List<Employee> employees = new ArrayList<>();
        Iterable projectmanagers = projectManagerRepository.findAll();
        Iterable salesmen = salesmanRepository.findAll();

        for(Object pm: projectmanagers){
            employees.add((ProjectManager) pm);
        }
        for(Object s: salesmen){
            employees.add((Salesman) s);
        }
        return employees;
    }
    /**
     * Counts all employes
     * @return returns the number of all employees.
     */
    public long count(){
        return countSalesman()+countProjectManagers();
    }
    /**Checks if an Employee with the given id exists
     *
     * @param id id of the Employee you want to look for
     * @return returns if an Employee with the id exists
     */
    public boolean exists(Long id){
        return (salesmanRepository.exists(id) || projectManagerRepository.exists(id));
    }


    //
    //Project Manager Services
    //

    /**
     * Saves an Iterable of ProjectManager
     * @param projectManagers Iterable of ProjectManager you want to save
     * @return retusn Iterable of persisted ProjectManager. Empty if none were found
     */
    public Iterable<ProjectManager> saveProjectManagers(Iterable<ProjectManager> projectManagers){
        return projectManagerRepository.save(projectManagers);
    }

    /**
     * Looks for one ProjectManager by Id
     * @param id id of the ProjectManager you want to load
     * @return returns ProjectManager or Null if no Entity was found.
     */
    public ProjectManager findProjectManagerById(long id){
        return projectManagerRepository.findOne(id);
    }

    /**Loads all ProjectManagers from the DB
     *
     * @return List of all available ProjectManagers. Empty if none were found.
     */
    public Iterable<ProjectManager> findAllProjectManagers(){
        return projectManagerRepository.findAll();
    }

    /**
     * Loads ProjectManagers with the given IDs
     *
     * @param ids List of ids of the ProjectManagers you want to load
     * @return List of all ProjectManagers with the given Ids. Empty if none were found.
     */
    public Iterable<ProjectManager> findAllProjectManagers(Iterable<Long> ids){
        return projectManagerRepository.findAll(ids);
    }

    /**
     * Loads all ProjectManager with the given Specialization
     *
     * @param specialization specialization you want to look for
     * @return List of all ProjectManagers with the given specialization. Empty if none were found.
     */
    public List<ProjectManager> findProjectManagerBySpecialization(String specialization){
        return projectManagerRepository.findBySpecialization(specialization);
    }

    /**
     * Loads all ProjectManager with the given lastName
     *
     * @param lastName lastName you want to look for
     * @return List of all ProjectManager with the given lastName. Empty if none were found.
     */
    public List<ProjectManager> findProjectManagerByLastName(String lastName){
        return projectManagerRepository.findByFirstName(lastName);
    }

    /**
     * Loads all ProjectManager with the given firstName
     *
     * @param firstName lastName you want to look for
     * @return List of all ProjectManagers with the given firstName. Empty if none were found.
     */
    public List<ProjectManager> findProjectManagerByFirstName(String firstName){
        return projectManagerRepository.findByLastName(firstName);
    }

    /**
     * Loads all ProjectManager with the given firstName and lastname
     *
     * @param firstName lastName you want to look for
     * @param lastName lastName you want to look for
     * @return List of all ProjectManagers with the given firstName and lastName. Empty if none were found.
     */
    public List<ProjectManager> findProjectManagernByFirstNameLastName(String firstName, String lastName){
        return projectManagerRepository.findByFirstNameLastName(firstName, lastName);
    }

    /**
     * counts all ProjectManager in the DB
     * @return number of ProjectManagers available
     */
    public long countProjectManagers(){
        return projectManagerRepository.count();
    }

    //
    // Salesmen Services
    //

    /**
     * Saves an Iterable of Salesman
     * @param salesmans Iterable of Salesman you want to save
     * @return retusn Iterable of persisted Salesman. Empty if none were found
     */
    public Iterable<Salesman> saveSalesman(Iterable<Salesman> salesmans){
        return salesmanRepository.save(salesmans);
    }

    /**
     * Looks for one Salesman by Id
     * @param id id of the Salesman you want to load
     * @return returns Salesman or Null if no Entity was found.
     */
    public Salesman findSalesmanById(long id){
        return salesmanRepository.findOne(id);
    }

    /**Loads all Salesman from the DB
     *
     * @return List of all available Salesman. Empty if none were found.
     */
    public Iterable<Salesman> findAllSalesmen(){
        return salesmanRepository.findAll();
    }

    /**
     * Loads Salesman with the given IDs
     *
     * @param ids List of ids of the Salesman you want to load
     * @return List of all Salesman with the given Ids. Empty if none were found.
     */
    public Iterable<Salesman> findAllSalesmen(Iterable<Long> ids){
        return salesmanRepository.findAll(ids);
    }

    /**
     * Loads all Salesman with the given Specialization
     *
     * @param specialization specialization you want to look for
     * @return List of all Salesman with the given specialization. Empty if none were found.
     */
    public List<Salesman> findSalesmanBySpecialization(String specialization){
        return salesmanRepository.findBySpecialization(specialization);
    }

    /**
     * Loads all Salesman with the given lastName
     *
     * @param lastName lastName you want to look for
     * @return List of all Salesman with the given lastName. Empty if none were found.
     */
    public List<Salesman> findSalesmanByLastName(String lastName){
        return salesmanRepository.findByFirstName(lastName);
    }

    /**
     * Loads all Salesman with the given firstName
     *
     * @param firstName lastName you want to look for
     * @return List of all Salesman with the given firstName. Empty if none were found.
     */
    public List<Salesman> findSalesmanByFirstName(String firstName){
        return salesmanRepository.findByLastName(firstName);
    }

    /**
     * Loads all Salesman with the given firstName and lastname
     *
     * @param firstName lastName you want to look for
     * @param lastName lastName you want to look for
     * @return List of all Salesman with the given firstName and lastName. Empty if none were found.
     */
    public List<Salesman> findSalesmanByFirstNameLastName(String firstName, String lastName){
        return salesmanRepository.findByFirstNameLastName(firstName, lastName);
    }
    /**
     * counts all Salesmen in the DB
     * @return number of Salesmen available
     */
    public long countSalesman(){
        return salesmanRepository.count();
    }



}
