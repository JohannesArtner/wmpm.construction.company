package com.routes.offerProcessor.routes;

import com.routes.offerProcessor.processors.IncomingMailProcessor;
import com.routes.offerProcessor.processors.TestDataProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by mionisation on 5/24/16.
 */
@Component
public class IncomingMail extends RouteBuilder {
    @Autowired
    TestDataProcessor testDataProcessor;
    @Override
    public void configure() throws Exception {
        //IncomingMailProcessor imp = new IncomingMailProcessor();
        //poll gmail inbox every 10 seconds
        InputStream in = new FileInputStream("config.properties");
        Properties p = new Properties();
        p.load(in);
        String login = p.getProperty("login");
        String pw = p.getProperty("pw");
        in.close();
        //process and route it
        String route = String.format("imaps://imap.gmail.com?username=%s&password=%s&delete=false&unseen=true&consumer.delay=10000", login, pw);
        from(route).process(testDataProcessor).to("seda:newOfferReply");
    }
}
