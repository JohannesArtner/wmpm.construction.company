package com.routes.requestInput.model;

import com.database.employeeDB.model.SpecializationType;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Wailzer
 * Model of incoming requests by Mail
 */
@ApiModel(description = "Represents an input of a request form")
public class MailInputModel implements Serializable {

    private String bodyWithInformation;
    private String sender;
    private String description;
    private SpecializationType specializationType;
    private Date dateFrom;
    private Date dateTo;
    private String location;
    private double squaremeter;
    private String companyName;
    private String clientFirstname;
    private String clientLastname;
    private String address;
    private String telephone;
    private String email;


    public MailInputModel(String bodyWithInformation, String sender){
        bodyWithInformation = this.bodyWithInformation;
        sender = this.sender;
    }

    public void setAllPossibleParameters() throws ParseException {
        email = sender;
        //Mail BODY = #ABC_Description#Hochbau#1.1.2016#20.1.2016#...
        String[] parts = bodyWithInformation.split("#");
        description  = parts[0];

        if(parts[1].equals("Hochbau")){
            SpecializationType sp = SpecializationType.HOCHBAU;
        }

        if(parts[1].equals("Tiefbau")){
            SpecializationType sp = SpecializationType.TIEFBAU;
        }

        dateFrom = setDateParsing(parts[2]);
        dateTo = setDateParsing(parts[3]);
        location = parts[4];
        squaremeter = Double.parseDouble(parts[5]);
        companyName = parts[6];
        clientFirstname = parts[7];
        clientLastname = parts[8];
        address = parts[9];
        telephone = parts[10];
    }


    private static Date setDateParsing(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.mm.yyyy", Locale.GERMAN);
        return format.parse(date);
    }

    public String getBodyWithInformation() {
        return bodyWithInformation;
    }

    public void setBodyWithInformation(String bodyWithInformation) {
        this.bodyWithInformation = bodyWithInformation;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
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

    public double getSquaremeter() {
        return squaremeter;
    }

    public void setSquaremeter(double squaremeter) {
        this.squaremeter = squaremeter;
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
