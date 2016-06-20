package com.routes.socialMediaProcessor.routes.routes;

import com.routes.socialMediaProcessor.routes.processors.FacebookProcessor;
import com.routes.socialMediaProcessor.routes.processors.TwitterProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

@Component
public class SocialMediaPublisher extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        InputStream in = new FileInputStream("config.properties");
        Properties p = new Properties();
        p.load(in);
        String twitterConsumerKey = p.getProperty("twitterConsumerKey");
        String twitterConsumerSecret = p.getProperty("twitterConsumerSecret");
        String twitterAccessToken = p.getProperty("twitterAccessToken");
        String twitterAccessTokenSecret = p.getProperty("twitterAccessTokenSecret");

        String facebookAppID = "1556856541283678";
        String facebookAppSecret = "6b5f95106e108e957bf7f2c42b4bd3a9";
        String facebookAccessToken = "EAAWH8ZBkcBV4BANwA1Na3iqjXEQOxvaN7D9vjITxGeoq2H182rpH03YgHwECOZBwK1Cf2GDrqaA8INwlcsUCZAYqzSV8BFKGUw3q36o4k6tEh9LxBHkvajZA4AHpcz0x85FZBQP9VlHZBzyDf6tvrAVsCDqW4KoZBLKwc1DfZBFJ5wZDZD";

        String twitterRoute = String.format("twitter://timeline/user?consumerKey=%s&consumerSecret=%s&accessToken=%s&accessTokenSecret=%s", twitterConsumerKey, twitterConsumerSecret, twitterAccessToken, twitterAccessTokenSecret);

        from("direct:createSocialMediaPost")

                .process(new TwitterProcessor())

                //.log("FACEBOOK PROCESSOR REACHED").
                //.process(new FacebookProcessor()).
                //.multicast().parallelProcessing()

                .to(twitterRoute)
                //.log("FACEBOOK .to REACHED")
                //.to("facebook://postFeed?inBody=postUpdate&oAuthAppId=1556856541283678&oAuthAppSecret=6b5f95106e108e957bf7f2c42b4bd3a9&oAuthAccessToken=EAAWH8ZBkcBV4BANwA1Na3iqjXEQOxvaN7D9vjITxGeoq2H182rpH03YgHwECOZBwK1Cf2GDrqaA8INwlcsUCZAYqzSV8BFKGUw3q36o4k6tEh9LxBHkvajZA4AHpcz0x85FZBQP9VlHZBzyDf6tvrAVsCDqW4KoZBLKwc1DfZBFJ5wZDZD&oAuthPermissions=publish_actions")


                        /*
                        //https://graph.facebook.com/oauth/access_token?client_id = YOUR_APP_ID & client_secret =YOUR_APP_SECRET&grant_type = client_credentials
                        //.setHeader("CamelHttpUri", constant("https://graph.facebook.com/endpoint?key=value&amp;access_token=1556856541283678|6b5f95106e108e957bf7f2c42b4bd3a9"))
                        //.to("https://www.facebook.com")

                         from("direct:processNewComments")
                .to("facebook://post?postId=" + header("FacebookCamel.postId"))
                .bean(facebookUpdatePostProcessor, "process")
                .to("mongodb:mongo?database={{mongodb.database}}&collection={{mongodb.facebookBcCollection}}&operation=save");
                        */

                //.to("facebook://postFeed?inBody=postUpdate?oAuthAppId=1556856541283678?oAuthAppSecret=6b5f95106e108e957bf7f2c42b4bd3a9?oAuthAccessToken=EAAWH8ZBkcBV4BANwA1Na3iqjXEQOxvaN7D9vjITxGeoq2H182rpH03YgHwECOZBwK1Cf2GDrqaA8INwlcsUCZAYqzSV8BFKGUw3q36o4k6tEh9LxBHkvajZA4AHpcz0x85FZBQP9VlHZBzyDf6tvrAVsCDqW4KoZBLKwc1DfZBFJ5wZDZD")
                .end();
    }
}