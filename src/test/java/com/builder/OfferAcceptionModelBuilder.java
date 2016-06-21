package com.builder;

import com.database.employeeDB.model.SpecializationType;
import com.routes.offerProcessor.model.OfferAcceptionModel;
import com.routes.requestInput.model.RestFormInputModel;

import java.util.Date;

/**
 * Created by rudolfplettenberg on 20.06.2016.
 */
public class OfferAcceptionModelBuilder {
    private OfferAcceptionModel oa = new OfferAcceptionModel();

    public  OfferAcceptionModelBuilder projectId(String projectId){
        oa.setProjectId(projectId);
        return this;
    }
    public  OfferAcceptionModelBuilder response(String response){
        oa.setResponse(response);
        return this;
    }

    public  OfferAcceptionModelBuilder responseNotes(String responseNotes){
        oa.setResponseNotes(responseNotes);
        return this;
    }

    public  OfferAcceptionModelBuilder customerName(String customerName){
        oa.setCustomerName(customerName);
        return this;
    }

    public  OfferAcceptionModelBuilder customerMail(String customerMail){
        oa.setCustomerMail(customerMail);
        return this;
    }

    public  OfferAcceptionModelBuilder projectManagerId(String projectManagerId){
        oa.setProjectManagerId(projectManagerId);
        return this;
    }
    public OfferAcceptionModel build(){
        return oa;
    }
}
