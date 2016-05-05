package com.database.employeeDB.model;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by rudolfplettenberg on 05.05.16.
 */
@Entity
public abstract class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NotNull
    private Date enteredCompany;
    private Date leftCompany;
    private String title;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String Address;
    private String telephone;
    private String email;

    public Employee() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getEnteredCompany() {
        return enteredCompany;
    }

    public void setEnteredCompany(Date enteredCompany) {
        this.enteredCompany = enteredCompany;
    }

    public Date getLeftCompany() {
        return leftCompany;
    }

    public void setLeftCompany(Date leftCompany) {
        this.leftCompany = leftCompany;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
