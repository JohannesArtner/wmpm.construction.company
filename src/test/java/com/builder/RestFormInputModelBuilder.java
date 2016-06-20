package com.builder;

import com.database.employeeDB.model.SpecializationType;
import com.routes.requestInput.model.RestFormInputModel;

import java.util.Date;

/**
 * Created by rudolfplettenberg on 20.06.2016.
 */
public class RestFormInputModelBuilder {
    private RestFormInputModel restFormInputModel = new RestFormInputModel();


    public  RestFormInputModelBuilder specializationType(SpecializationType specializationType){
        restFormInputModel.setSpecializationType(specializationType);
        return this;
    }
    public  RestFormInputModelBuilder dateFrom(Date dateFrom){
        restFormInputModel.setDateFrom(dateFrom);
        return this;
    }
    public  RestFormInputModelBuilder dateTo(Date dateTo){
        restFormInputModel.setDateTo(dateTo);
        return this;
    }
    public  RestFormInputModelBuilder location(String location){
        restFormInputModel.setLocation(location);
        return this;
    }
    public  RestFormInputModelBuilder squaremeter(double squaremeter){
        restFormInputModel.setSquaremeter(squaremeter);
        return this;
    }

    public RestFormInputModelBuilder companyName(String companyName){
        restFormInputModel.setCompanyName(companyName);
        return this;
    }
    public RestFormInputModelBuilder address(String address){
        restFormInputModel.setAddress(address);
        return this;
    }
    public RestFormInputModelBuilder telephone(String telephone){
        restFormInputModel.setTelephone(telephone);
        return this;
    }
    public RestFormInputModelBuilder email(String email){
        restFormInputModel.setEmail(email);
        return this;
    }
    public RestFormInputModelBuilder clientFirstname(String clientFirstname){
        restFormInputModel.setClientLastname(clientFirstname);
        return this;
    }
    public RestFormInputModelBuilder clientLastname(String clientLastname){
        restFormInputModel.setClientLastname(clientLastname);
        return this;
    }
    public RestFormInputModelBuilder description(String description){
        restFormInputModel.setDescription(description);
        return this;
    }


    public RestFormInputModel build(){
        return restFormInputModel;
    }
}
