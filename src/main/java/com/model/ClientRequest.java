package com.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by Johannes on 04.05.2016.
 */

public class ClientRequest {
    @Id
    private int id;
    private String forename;
    private String surname;
    private String emailAddress;
    private String location;
    private String description;
    private Date dateFrom;
    private Date dateTo;

    public ClientRequest(String forename, String surname, String emailAddress, String location, String description, Date dateFrom, Date dateTo) {
        this.forename = forename;
        this.surname = surname;
        this.emailAddress = emailAddress;
        this.location = location;
        this.description = description;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public int getId() {
        return id;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}
