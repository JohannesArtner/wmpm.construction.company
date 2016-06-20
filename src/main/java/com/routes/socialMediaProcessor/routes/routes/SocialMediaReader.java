package com.routes.socialMediaProcessor.routes.routes;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

@Component
public class SocialMediaReader extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        /** Permissions denied. App has to be reviewed by Facebook in order to use the permission "user_posts" to read the posts on a users timeline **/
        /*
        from("facebook://home?oAuthAppId=1556856541283678&oAuthAppSecret=6b5f95106e108e957bf7f2c42b4bd3a9&oAuthAccessToken=EAAWH8ZBkcBV4BAIr64UYkDcQJ3QSDEGel3ZCud78ItPi61ENkbK6nQKbsxgd9eFUrLtx9ZAcA882ss3ce7jWlXZBBv29ywmdNv1kXropWSBPiEmmjConR7I1bqK4homPUuZBFv5hSZB2ESOjlIDuZB3bV4in47n6CRZAMBKzEBr5qwZDZD&oAuthPermissions=user_posts&consumer.delay=5000")
                .log("FACEBOOK READER .to REACHED")
                .to("mock:bean:blah");

        */
    }
}
