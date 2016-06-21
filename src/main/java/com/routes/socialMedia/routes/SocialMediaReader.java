package com.routes.socialMedia.routes;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SocialMediaReader extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        /** Permissions denied. App has to be reviewed by Facebook in order to use the permission "user_posts" to read the posts on a users timeline **/
        /** Public information can be retrieved but not more until reviewing**/

        from("facebook://me?oAuthAppId=1556856541283678&oAuthAppSecret=6b5f95106e108e957bf7f2c42b4bd3a9&oAuthAccessToken=EAAWH8ZBkcBV4BAKjZBuii1T5twxI3d0drQKhUmmLTCyJS3yWkTwtV4cwWQUIzpQJ1uldujvEiSQd4Dxvn4wmEoit98VBIoHh842pulLFoZBMFIR4a8u6LLQldwVSaOid3TTvcEu9mRic3gt0NdaPpqDNZAE1waMe85FJDkTheAZDZD&consumer.delay=100000")
                .log(LoggingLevel.INFO, "SocialMediaReader", "FACEBOOK Info: ${body}")
                .to("mock:bean:blah");

    }
}
