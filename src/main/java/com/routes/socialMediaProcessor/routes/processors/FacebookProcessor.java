package com.routes.socialMediaProcessor.routes.processors;

import com.routes.offerProcessor.model.OfferAcceptionModel;
import facebook4j.*;
import facebook4j.auth.AccessToken;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultMessage;
import org.apache.log4j.Logger;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FacebookProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        OfferAcceptionModel oa = (OfferAcceptionModel)exchange.getIn().getBody();


        PostUpdate post = new PostUpdate(new URL("http://facebook4j.org"))
                .picture(new URL("http://facebook4j.org/images/hero.png"))
                .name("Facebook4J - A Java library for the Facebook Graph API")
                .caption("facebook4j.org")
                .description("Name: " + oa.getCustomerName());


        exchange.getOut().setBody(post);

        /* Facebook facebook = new FacebookFactory().getInstance();

        String appId = "1556856541283678";
        String appSecret = "6b5f95106e108e957bf7f2c42b4bd3a9";
        String accessToken = "EAAWH8ZBkcBV4BANwA1Na3iqjXEQOxvaN7D9vjITxGeoq2H182rpH03YgHwECOZBwK1Cf2GDrqaA8INwlcsUCZAYqzSV8BFKGUw3q36o4k6tEh9LxBHkvajZA4AHpcz0x85FZBQP9VlHZBzyDf6tvrAVsCDqW4KoZBLKwc1DfZBFJ5wZDZD";

        facebook.setOAuthAppId(appId, appSecret);
        facebook.setOAuthPermissions(null);
        AccessToken token = facebook.getOAuthAppAccessToken();
        facebook.setOAuthAccessToken(token);

        facebook.postStatusMessage("Hello World from Facebook4J.");

        exchange.getOut().setBody(facebook);*/

    }
}
