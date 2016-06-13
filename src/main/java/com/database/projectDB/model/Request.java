package com.database.projectDB.model;

import com.database.employeeDB.model.SpecializationType;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by rudolfplettenberg on 07.05.16.
 */
@Entity
public class Request {
    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("clientId")
    private long clientId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("specializationType")
    private SpecializationType specializationType;

    @JsonProperty("dateFrom")
    private Date dateFrom;

    @JsonProperty("dateTo")
    private Date dateTo;

    @JsonProperty("location")
    private String location;

    @JsonProperty("squaremeters")
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
