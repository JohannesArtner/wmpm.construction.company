package com.database.projectDB.model;

import com.database.employeeDB.model.SpecializationType;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by rudolfplettenberg on 07.05.16.
 */
@Entity(name = "Request")
@Table(name = "request")
public class Request {
    @Id
    @JsonProperty("id")
    private long id;

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

    @JsonProperty("emailCustomer")
    private String emailCustomer;

    public String getEmailCustomer() {
        return emailCustomer;
    }

    public void setEmailCustomer(String emailCustomer) {
        this.emailCustomer = emailCustomer;
    }

    private boolean read = false;

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Double getSquaremeters() {
        return squaremeters;
    }

    public void setSquaremeters(Double squaremeters) {
        this.squaremeters = squaremeters;
    }

    public Request() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (id != request.id) return false;
        if (clientId != request.clientId) return false;
        if (read != request.read) return false;
        if (description != null ? !description.equals(request.description) : request.description != null) return false;
        if (specializationType != request.specializationType) return false;
        if (dateFrom != null ? !dateFrom.equals(request.dateFrom) : request.dateFrom != null) return false;
        if (dateTo != null ? !dateTo.equals(request.dateTo) : request.dateTo != null) return false;
        if (location != null ? !location.equals(request.location) : request.location != null) return false;
        if (squaremeters != null ? !squaremeters.equals(request.squaremeters) : request.squaremeters != null)
            return false;
        return emailCustomer != null ? emailCustomer.equals(request.emailCustomer) : request.emailCustomer == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (clientId ^ (clientId >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (specializationType != null ? specializationType.hashCode() : 0);
        result = 31 * result + (dateFrom != null ? dateFrom.hashCode() : 0);
        result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (squaremeters != null ? squaremeters.hashCode() : 0);
        result = 31 * result + (emailCustomer != null ? emailCustomer.hashCode() : 0);
        result = 31 * result + (read ? 1 : 0);
        return result;
    }
}
