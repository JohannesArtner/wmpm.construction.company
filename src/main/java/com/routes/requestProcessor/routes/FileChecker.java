package com.routes.requestProcessor.routes;

import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.util.ByteArrayDataSource;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Johannes on 10.06.2016.
 */
@org.springframework.stereotype.Component
public class FileChecker extends RouteBuilder {
    static Logger logger = Logger.getLogger(SendMail.class);

    @Autowired
    public CamelContext context;

    @Override
    public void configure() throws Exception {
        String path = "C:/Temp/camel/offerToPdf";

        from("file://" + path + "?noop=true&move=.done").to("direct:sendViaEmail");

    }
}
