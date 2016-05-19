package com.services;

import com.database.clientDB.ClientDAO;
import com.database.employeeDB.model.SpecializationType;
import com.database.projectDB.OfferDAO;
import com.database.projectDB.ProjectDAO;
import com.database.projectDB.RequestDAO;
import com.database.projectDB.model.Offer;
import com.database.projectDB.model.Request;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created by Johannes on 04.05.2016.
 */
@Service
public class RequestService {

    @Autowired
    private RequestDAO requestDAO;

    @Autowired
    private OfferDAO offerDAO;

    @PostConstruct
    public void init() {
        System.out.println("service from @service");
    }

    public void createNewRequest(Request clientRequest){
        requestDAO.save(clientRequest);
    }

    public Document generatePDF(Offer offer) throws DocumentException {

        Document document = new Document(PageSize.LETTER);
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph("Hello!"));
        document.add(new Paragraph("Concerning your inquiry: " + offer.getDescription()));
        // step 5
        document.close();


        return document;
    }

    public Offer createOffer(Request request){
        Offer offer = new Offer();
        offer.setDescription(request.getDescription());
        offer.setClientId(request.getClientId());
        offer.setCreatedAt(new Date());
        offer.setEstimated_days(request.getSquaremeters().intValue() * 10);
        offer.setFinishDate(new Date(new Date().getTime() + offer.getEstimated_days() * 1000 * 60 * 60 * 24 * 5));
        offer.setStartDate(new Date(new Date().getTime() +  1000 * 60 * 60 * 24 * 5));
        if(request.getSpecializationType().equals(SpecializationType.HOCHBAU)){
            offer.setManHourCosts(request.getSquaremeters().intValue() * 200);
            offer.setMaterialcosts(request.getSquaremeters().intValue() * 4);
        } else {
            offer.setManHourCosts(request.getSquaremeters().intValue() * 5);
            offer.setMaterialcosts(request.getSquaremeters().intValue() * 2);
        }

        return offer;
    }

    public void storeOfferToMongoDB(Offer offer){
        offerDAO.save(offer);
    }

}
