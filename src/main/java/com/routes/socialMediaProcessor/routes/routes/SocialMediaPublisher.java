package com.routes.socialMediaProcessor.routes.routes;

import com.Processors.LoggingProcessor;
import com.routes.offerProcessor.model.OfferAcceptionModel;
import com.routes.socialMediaProcessor.routes.processors.DatabaseProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
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

        //process and route it
        String twitterRoute = String.format("twitter://timeline/user?consumerKey=%s&consumerSecret=%s&accessToken=%s&accessTokenSecret=%s", twitterConsumerKey, twitterConsumerSecret, twitterAccessToken, twitterAccessTokenSecret);

        from("direct:createSocialMediaPost")

                .process(new DatabaseProcessor()).

                multicast().parallelProcessing()

                .to(twitterRoute)
                .log("TWITTER REACHED")


                        //https://graph.facebook.com/oauth/access_token?client_id = YOUR_APP_ID & client_secret =YOUR_APP_SECRET&grant_type = client_credentials
                        //.setHeader("CamelHttpUri", constant("https://graph.facebook.com/endpoint?key=value&amp;access_token=1556856541283678|6b5f95106e108e957bf7f2c42b4bd3a9"))
                        //.to("https://www.facebook.com")

                        //.to("twitter://timeline/user?consumerKey=CrVdcIxI1s9vGo6lYM2AB3icG&consumerSecret=sqnGHKHmFBbtOSB1BwA0Trfiyoi0pSg1bMNUhnOXYEwBeFPuat&accessToken=734281955454427136-tNvFD0XS89Yi0QdSgghCeuuRFyERaOj&accessTokenSecret=rFpmvLGSOd4HCtYlcSfHPUQgrw3SGzbAvv8MM4zkXo1I8")
                /*.to("facebook://postFeed?inBody=postUpdate")
                .to("facebook://endpoint[?options]")
                .convertBodyTo(String.class)
                .to("log:FacebookDump")*/
                .end();
    }
}