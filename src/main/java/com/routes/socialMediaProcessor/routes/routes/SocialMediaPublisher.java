package com.routes.socialMediaProcessor.routes.routes;

import com.routes.socialMediaProcessor.routes.processors.FacebookProcessor;
import com.routes.socialMediaProcessor.routes.processors.TwitterProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

@Component
public class SocialMediaPublisher extends RouteBuilder {
    static Logger logger = Logger.getLogger(SocialMediaPublisher.class.getName());

    @Override
    public void configure() throws Exception {

        InputStream in = new FileInputStream("config.properties");
        Properties p = new Properties();
        p.load(in);
        String twitterConsumerKey = p.getProperty("twitterConsumerKey");
        String twitterConsumerSecret = p.getProperty("twitterConsumerSecret");
        String twitterAccessToken = p.getProperty("twitterAccessToken");
        String twitterAccessTokenSecret = p.getProperty("twitterAccessTokenSecret");

        String twitterRoute = String.format("twitter://timeline/user?consumerKey=%s&consumerSecret=%s&accessToken=%s&accessTokenSecret=%s", twitterConsumerKey, twitterConsumerSecret, twitterAccessToken, twitterAccessTokenSecret);

        errorHandler(deadLetterChannel("jms:queue:dead"));

        from("direct:createSocialMediaPost")

                //.multicast()

                .process(new TwitterProcessor())
                .log("TWITTER PROCESSOR REACHED")
                .to(twitterRoute)


                .process(new FacebookProcessor())
                .log("FACEBOOK .to REACHED")
                .to("facebook://postFeed?inBody=postUpdate&oAuthAppId=1556856541283678&oAuthAppSecret=6b5f95106e108e957bf7f2c42b4bd3a9&oAuthAccessToken=EAAWH8ZBkcBV4BAIr64UYkDcQJ3QSDEGel3ZCud78ItPi61ENkbK6nQKbsxgd9eFUrLtx9ZAcA882ss3ce7jWlXZBBv29ywmdNv1kXropWSBPiEmmjConR7I1bqK4homPUuZBFv5hSZB2ESOjlIDuZB3bV4in47n6CRZAMBKzEBr5qwZDZD&oAuthPermissions=publish_actions")

                .to("jms:queue:dead");


    }
}