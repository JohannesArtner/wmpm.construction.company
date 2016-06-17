
package com.configuration;

import org.apache.camel.component.google.calendar.GoogleCalendarComponent;
import org.apache.camel.component.google.calendar.GoogleCalendarConfiguration;
import org.springframework.context.annotation.*;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


@Configuration
@ComponentScan(basePackages = {"com.configuration", "com.routes.offerProcessor.routes"})
public class GoogleConfigurations{
    //import from camel or spring?

    @Bean(name = "google-calendar")
    public GoogleCalendarComponent googleCalendarComponent() throws Exception {
        //double entry, not sure if method above gets executed
        InputStream in = new FileInputStream("config.properties");
        Properties p = new Properties();
        p.load(in);
        String googleclientId = p.getProperty("googleclientId");
        String googleclientSecret = p.getProperty("googleclientSecret");
        String googlerefreshToken = p.getProperty("googlerefreshToken");
        String googleaccessToken = p.getProperty("googleaccessToken");
        String googleapplicationName = p.getProperty("googleapplicationName");

        in.close();
        GoogleCalendarComponent googleCalendarComponent = new GoogleCalendarComponent();
        GoogleCalendarConfiguration config = new GoogleCalendarConfiguration();
        config.setClientId(googleclientId);
        config.setClientSecret(googleclientSecret);
        config.setRefreshToken(googlerefreshToken);
        config.setApplicationName(googleapplicationName);
        config.setAccessToken(googleaccessToken);
        googleCalendarComponent.setConfiguration(config);

        return googleCalendarComponent;
    }
}