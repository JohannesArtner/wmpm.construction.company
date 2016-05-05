package com.database.employeeDB.model;

import com.database.employeeDB.model.Employee;
import com.database.employeeDB.model.SpecializationType;
import com.sun.istack.NotNull;

import javax.persistence.Entity;

/**
 * Created by rudolfplettenberg on 05.05.16.
 */
@Entity
public class ProjectManager extends Employee {

    @NotNull
    private SpecializationType specialization;

    public ProjectManager() {
    }

    public SpecializationType getSpecialization() {
        return specialization;
    }

    public void setSpecialization(SpecializationType specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {

        return "ProjectManager{" +
                super.toString() +
                "specialization=" + specialization +
                '}';
    }
}
