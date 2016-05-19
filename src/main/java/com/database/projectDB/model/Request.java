package com.database.projectDB.model;

import com.database.employeeDB.model.SpecializationType;

import org.springframework.data.annotation.Id;
import java.util.Date;

/**
 * Created by rudolfplettenberg on 07.05.16.
 */
public class Request {
    @Id
    private String id;

    private long clientId;

    private String description;
    private SpecializationType specializationType;
    private Date dateFrom;
    private Date dateTo;
    private String location;
    private Double squaremeters;

    public Double getSquaremeters() {
        return squaremeters;
    }

    public void setSquaremeters(Double squaremeters) {
        this.squaremeters = squaremeters;
    }

    public Request() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SpecializationType getSpecializationType() {
        return specializationType;
    }

    public void setSpecializationType(SpecializationType specializationType) {
        this.specializationType = specializationType;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id='" + id + '\'' +
                ", clientId=" + clientId +
                ", description='" + description + '\'' +
                ", specializationType=" + specializationType +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", location='" + location + '\'' +
                '}';
    }
}
