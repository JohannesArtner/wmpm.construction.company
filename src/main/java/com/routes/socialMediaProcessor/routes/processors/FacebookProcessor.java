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

        Facebook facebook = new FacebookFactory().getInstance();

        String appId = "1556856541283678";
        String appSecret = "6b5f95106e108e957bf7f2c42b4bd3a9";
        String accessToken = "EAAWH8ZBkcBV4BABT8xBeMvXSE51gjyChW2FjBokNCyOA79sVvdSGq4KymhdC3TZCVPn6xbRdNwrlkwY0hjPu7Ll0yph9GbLyZCW0CIXFY84G6kIgEVHhLhZBHW10hsTrLWjpaokg23DvJmiNEZCoAT2QSHJoNTnd0UomfZBcsuNgZDZD";

        facebook.setOAuthAppId(appId, appSecret);
        facebook.setOAuthPermissions("publish_actions");
        facebook.setOAuthAccessToken(new AccessToken(accessToken, null));

        PostUpdate post = new PostUpdate(new URL("http://facebook4j.org"))
                .picture(new URL("http://facebook4j.org/images/hero.png"))
                .name("Facebook4J - A Java library for the Facebook Graph API")
                .caption("facebook4j.org")
                .description(oa.getCustomerName() + " // " + oa.getCustomerMail());

        //facebook.postFeed(post);

        exchange.getOut().setBody(post);

    }
}
