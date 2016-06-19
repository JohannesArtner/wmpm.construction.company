package com.database.projectDB.model;

import com.database.employeeDB.model.SpecializationType;
import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by rudolfplettenberg on 07.05.16.
 */
@Entity(name = "Offer")
@NamedQuery(name = "selectAllOpen", query = "select o from com.database.projectDB.model.Offer o where o.sent=false")
public class Offer {
    @Id
    private long id;

    private long salesmanId;
    private Date createdAt;
    private long clientId;

    @Transient
    private Request request;
    private String description;

    private boolean read = false;
    private boolean sent = false;

    //Time
    private Date startDate;
    private Date finishDate;
    private int estimated_days;

    //Costs
    private double manhours;
    private double manHourCosts;
    private double materialcosts;
    private String emailCostumer;

    public Offer() {
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(long salesmanId) {
        this.salesmanId = salesmanId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public int getEstimated_days() {
        return estimated_days;
    }

    public void setEstimated_days(int estimated_days) {
        this.estimated_days = estimated_days;
    }

    public double getManhours() {
        return manhours;
    }

    public void setManhours(double manhours) {
        this.manhours = manhours;
    }

    public double getManHourCosts() {
        return manHourCosts;
    }

    public void setManHourCosts(double manHourCosts) {
        this.manHourCosts = manHourCosts;
    }

    public double getMaterialcosts() {
        return materialcosts;
    }

    public void setMaterialcosts(double materialcosts) {
        this.materialcosts = materialcosts;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "\n id='" + id + '\'' +
                "\n, salesmanId=" + salesmanId +
                "\n, createdAt=" + createdAt +
                "\n, clientId=" + clientId +
                "\n, request=" + request +
                "\n, description='" + description + '\'' +
                "\n, startDate=" + startDate +
                "\n, finishDate=" + finishDate +
                "\n, estimated_days=" + estimated_days +
                "\n, manhours=" + manhours +
                "\n, manHourCosts=" + manHourCosts +
                "\n, materialcosts=" + materialcosts +
                '}';
    }

    public void setEmailCostumer(String emailCostumer) {
        this.emailCostumer = emailCostumer;
    }

    public String getEmailCostumer() {
        return emailCostumer;
    }
}
