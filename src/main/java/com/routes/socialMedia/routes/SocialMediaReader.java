package com.routes.socialMedia.routes;
import com.routes.socialMedia.processors.ErrorProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SocialMediaReader extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        /** Permissions denied. App has to be reviewed by Facebook in order to use the permission "user_posts" to read the posts on a users timeline **/
        /** To show that the Facebook route is established: Public information can be retrieved but not more until reviewing**/

        from("facebook://me?oAuthAppId=1556856541283678&oAuthAppSecret=6b5f95106e108e957bf7f2c42b4bd3a9&oAuthAccessToken=EAAWH8ZBkcBV4BACUV6tvjqQo7JxNQDGIwzwPxyMjliCNTrPZB3L38ZBUn3fYlH9GPQf1WTEsgatg1yuSHB2M7gGsgNggOCfUwKQatM119yS74HZBc9Yua9q87wvhS7us8U9ZCWlmOZAxR9ohN3gNnh5gZBAg01FLeULPRLZAwrSHEwZDZD&consumer.delay=100000")
                .log(LoggingLevel.INFO, "SocialMediaReader", "FACEBOOK Info: ${body}")
                .to("mock:bean:test");

    }
}
