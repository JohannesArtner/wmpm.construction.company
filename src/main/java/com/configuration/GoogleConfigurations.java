
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

    @Bean
    public GoogleCalendarConfiguration googleCalendarConfiguration() {
        GoogleCalendarConfiguration config = new GoogleCalendarConfiguration();
        String clientId = "716865151150-iccnrc4oqli46h1fq9n19143j87gtgff.apps.googleusercontent.com";
        String clientSecret = "zHIhFsFufkggx2Vu7R_-0sMc&accessToken=ya29.Ci8DA4Ae-BELIHQT3s5l8DMtq5587ulAFAS1SHteYuK_DAAFn95DvQ2POoDsH8nk8g";
        String refreshToken = "1/LABZFR1KNzgI_U2VO5xyE82V0TKjTj1knj6EEJK5seg";
        String applicationName = "ConstructionCompany";
        config.setClientId(clientId);
        config.setClientSecret(clientSecret);
        config.setRefreshToken(refreshToken);
        config.setApplicationName(applicationName);

        return config;
    }

    @Bean(name = "google-calendar")
    public GoogleCalendarComponent googleCalendarComponent() throws Exception {
        //double entry, not sure if method above gets executed
        GoogleCalendarComponent googleCalendarComponent = new GoogleCalendarComponent();
        String clientId = "716865151150-iccnrc4oqli46h1fq9n19143j87gtgff.apps.googleusercontent.com";
        String clientSecret = "zHIhFsFufkggx2Vu7R_-0sMc&accessToken=ya29.Ci8DA4Ae-BELIHQT3s5l8DMtq5587ulAFAS1SHteYuK_DAAFn95DvQ2POoDsH8nk8g";
        String refreshToken = "1/LABZFR1KNzgI_U2VO5xyE82V0TKjTj1knj6EEJK5seg";
        String applicationName = "ConstructionCompany";
        GoogleCalendarConfiguration config = new GoogleCalendarConfiguration();
        config.setClientId(clientId);
        config.setClientSecret(clientSecret);
        config.setRefreshToken(refreshToken);
        config.setApplicationName(applicationName);
        googleCalendarComponent.setConfiguration(config);

        return googleCalendarComponent;
    }
}