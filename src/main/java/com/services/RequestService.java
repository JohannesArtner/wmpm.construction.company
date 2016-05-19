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

    public void storeOfferToMongoDB(Offer offer){
        offerDAO.save(offer);
    }

}
