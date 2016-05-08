package com.database.projectDB.model;

import com.database.employeeDB.model.SpecializationType;
import com.sun.istack.NotNull;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by rudolfplettenberg on 07.05.16.
 */
public class Offer {
    @Id
    private String id;

    @NotNull
    private long salesmanId;
    private Date createdAt;
    @NotNull
    private long clientId;

    private Request request;
    private String description;

    //Time
    private Date startDate;
    private Date finishDate;
    private int estimated_days;

    //Costs
    private double manhours;
    private double manHourCosts;
    private double materialcosts;

    public Offer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
                "id='" + id + '\'' +
                ", salesmanId=" + salesmanId +
                ", createdAt=" + createdAt +
                ", clientId=" + clientId +
                ", request=" + request +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", estimated_days=" + estimated_days +
                ", manhours=" + manhours +
                ", manHourCosts=" + manHourCosts +
                ", materialcosts=" + materialcosts +
                '}';
    }
}
