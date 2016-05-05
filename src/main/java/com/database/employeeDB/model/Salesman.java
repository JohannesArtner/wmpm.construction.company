package com.database.employeeDB.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

/**
 * Created by rudolfplettenberg on 05.05.16.
 */
@Entity
public class Salesman extends Employee {
    @NotNull
    private SpecializationType specialization;

    public Salesman() {
    }

    public SpecializationType getSpecialization() {
        return specialization;
    }

    public void setSpecialization(SpecializationType specialization) {
        this.specialization = specialization;
    }
}
