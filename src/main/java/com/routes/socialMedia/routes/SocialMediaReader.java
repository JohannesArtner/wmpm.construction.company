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

        from("facebook://me?oAuthAppId=1556856541283678&oAuthAppSecret=6b5f95106e108e957bf7f2c42b4bd3a9&oAuthAccessToken=EAAWH8ZBkcBV4BAHlZBFbZBoee5tAcgSVCHD5DJnwrJ54eIcxa9ELmWMiRI3b4QXTf0PXE7Ft2nZBFPP1L3hRA5YFQwhc3DyoS96h8SjgYH2lcMk29VMZAk3Yg0WxtPDGEtZBAZBMSgSL66akOLkes7OZBXNYEPNKECCuAXrrEktWuQZDZD&consumer.delay=100000")
                .log(LoggingLevel.INFO, "SocialMediaReader", "FACEBOOK Info: ${body}")
                .to("mock:bean:test");

    }
}
