
package com.configuration;

import org.apache.camel.component.google.calendar.GoogleCalendarComponent;
import org.apache.camel.component.google.calendar.GoogleCalendarConfiguration;
import org.springframework.context.annotation.*;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {"com.configuration", "com.routes.offerProcessor.routes"})
public class GoogleConfigurations{
    //import from camel or spring?

    @Bean(name = "google-calendar")
    public GoogleCalendarComponent googleCalendarComponent() throws Exception {
        //double entry, not sure if method above gets executed
        GoogleCalendarComponent googleCalendarComponent = new GoogleCalendarComponent();
        String clientId = "716865151150-dlau4uabcejen6lnrh53s4ddua0qpmp9.apps.googleusercontent.com";
        String clientSecret = "uJYmNBKA9JEGvnZRiseTlAbP";
        String refreshToken = "1/swHBO1aEeWiubsF7yZacM805f25Ma3SvB5hy0fdgBbc";
        String accessToken = "ya29.Ci8EAw6dazn60vAy-pp2qK3jHnHIBHD9_WrMngI8aG9_EKKu1EROSHVpPWbf3GYYbw";
        String applicationName = "ConstructionCompany";
        GoogleCalendarConfiguration config = new GoogleCalendarConfiguration();
        config.setClientId(clientId);
        config.setClientSecret(clientSecret);
        config.setRefreshToken(refreshToken);
        config.setApplicationName(applicationName);
        config.setAccessToken(accessToken);
        googleCalendarComponent.setConfiguration(config);

        return googleCalendarComponent;
    }
}