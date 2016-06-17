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
        String message = "New construction is happening";
        String route = String.format("google-calendar://events/quickAdd?calendarId=%s&text=%s", "offerManagementConstructionCom@gmail.com", message);

        from("direct:createGoogleCalendar")
        .to(route);
    }
}
