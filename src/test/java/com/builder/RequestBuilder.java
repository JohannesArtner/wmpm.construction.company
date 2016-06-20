package com.builder;

import com.database.employeeDB.model.SpecializationType;
import com.database.projectDB.model.Request;

import java.util.Date;

/**
 * Created by rudolfplettenberg on 20.06.2016.
 */
public class RequestBuilder {
    private Request request = new Request();

    public RequestBuilder id(String id){
        request.setId(id);
        return this;
    }

    public  RequestBuilder specializationType(SpecializationType specializationType){
        request.setSpecializationType(specializationType);
        return this;
    }
    public  RequestBuilder dateFrom(Date dateFrom){
        request.setDateFrom(dateFrom);
        return this;
    }
    public  RequestBuilder dateTo(Date dateTo){
        request.setDateTo(dateTo);
        return this;
    }
    public  RequestBuilder location(String location){
        request.setLocation(location);
        return this;
    }
    public  RequestBuilder squaremeter(double squaremeter){
        request.setSquaremeters(squaremeter);
        return this;
    }

    public RequestBuilder description(String description){
        request.setDescription(description);
        return this;
    }


    public Request build(){
        return request;
    }
}
