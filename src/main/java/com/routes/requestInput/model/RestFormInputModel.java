package com.routes.requestInput.model;

import com.database.employeeDB.model.SpecializationType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Model of incoming requests
 */
@ApiModel(description = "Represents an input of a request form")
public class RestFormInputModel implements Serializable {

    @ApiModelProperty(value = "Description of the project", required = false)
    private String description;
    @ApiModelProperty(value = "'Hochbau' or 'Tiefbau'", required = true)
    private SpecializationType specializationType;
    @ApiModelProperty(value = "Starting date of project", required = false)
    private Date dateFrom;
    @ApiModelProperty(value = "End date of project", required = false)
    private Date dateTo;
    @ApiModelProperty(value = "Location of Project", required = false)
    private String location;
    @ApiModelProperty(value = "Total Squaremeters of project", required = false)
    private double squaremeter;

    @ApiModelProperty(value = "Companyname of Customer", required = false)
    private String companyName;
    @ApiModelProperty(value = "First Name of Customer", required = false)
    private String clientFirstname;
    @ApiModelProperty(value = "Last Name of Customer", required = false)
    private String clientLastname;
    @ApiModelProperty(value = "Address of Customer", required = false)
    private String address;
    @ApiModelProperty(value = "Telephone number of Customer", required = false)
    private String telephone;
    @ApiModelProperty(value = "Email of Customer", required = true)
    private String email;



    public RestFormInputModel() {
    }

    public double getSquaremeter() {
        return squaremeter;
    }

    public void setSquaremeter(double squaremeter) {
        this.squaremeter = squaremeter;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getClientFirstname() {
        return clientFirstname;
    }

    public void setClientFirstname(String clientFirstname) {
        this.clientFirstname = clientFirstname;
    }

    public String getClientLastname() {
        return clientLastname;
    }

    public void setClientLastname(String clientLastname) {
        this.clientLastname = clientLastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Override
    public String toString() {
        return "RestFormInputModel{" +
                "description='" + description + '\'' +
                ", specializationType=" + specializationType +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", location='" + location + '\'' +
                ", companyName='" + companyName + '\'' +
                ", clientFirstname='" + clientFirstname + '\'' +
                ", clientLastname='" + clientLastname + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
