package com.routes.socialMedia.routes;

import com.routes.socialMedia.processors.ErrorProcessor;
import com.routes.socialMedia.processors.FacebookProcessor;
import com.routes.socialMedia.processors.TwitterProcessor;
import facebook4j.FacebookException;
import org.apache.camel.RuntimeCamelException;
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

        //errorHandler(deadLetterChannel("jms:queue:dead"));

        //onException(Exception.class).to("jms:queue:dead");

        from("direct:createSocialMediaPost")


                .process(new TwitterProcessor())
                .log("TWITTER PROCESSOR REACHED")
                .to(twitterRoute)


                .process(new FacebookProcessor())
                .log("FACEBOOK PROCESSOR REACHED")
                .onException(Exception.class)
                .process(new ErrorProcessor())
                .handled(true)

                .to("facebook://postFeed?inBody=postUpdate&oAuthAppId=1556856541283678&oAuthAppSecret=6b5f95106e108e957bf7f2c42b4bd3a9&oAuthAccessToken=EAAWH8ZBkcBV4BAKjZBuii1T5twxI3d0drQKhUmmLTCyJS3yWkTwtV4cwWQUIzpQJ1uldujvEiSQd4Dxvn4wmEoit98VBIoHh842pulLFoZBMFIR4a8u6LLQldwVSaOid3TTvcEu9mRic3gt0NdaPpqDNZAE1waMe85FJDkTheAZDZD&oAuthPermissions=publish_actions");


    }
}