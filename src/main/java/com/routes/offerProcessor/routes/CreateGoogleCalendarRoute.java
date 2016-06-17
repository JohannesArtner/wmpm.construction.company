package com.routes.offerProcessor.routes;


import com.google.api.services.calendar.model.Event;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.google.calendar.GoogleCalendarComponent;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.apache.camel.component.google.calendar.GoogleCalendarConfiguration;

/**
 * Created by mionisation on 6/9/16.
 */
@Component
@DependsOn({"google-calendar"})
public class CreateGoogleCalendarRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        Event event = new Event();

/*
        String clientId = "716865151150-iccnrc4oqli46h1fq9n19143j87gtgff.apps.googleusercontent.com";
        String clientSecret = "zHIhFsFufkggx2Vu7R_-0sMc&accessToken=ya29.Ci8DA4Ae-BELIHQT3s5l8DMtq5587ulAFAS1SHteYuK_DAAFn95DvQ2POoDsH8nk8g";
        String refreshToken = "1/LABZFR1KNzgI_U2VO5xyE82V0TKjTj1knj6EEJK5seg";
        GoogleCalendarConfiguration config = new GoogleCalendarConfiguration();
        config.setClientId(clientId);
        config.setClientSecret(clientSecret);
        config.setRefreshToken(refreshToken);
        */

        String route = "google-calendar://events/quickAdd?calendarId=mycalendar&text=e";
        String e = "New construction is happening";
        from("direct:createGoogleCalendar")
        .to(route);
    }
}
