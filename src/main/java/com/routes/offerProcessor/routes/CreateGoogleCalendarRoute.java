package com.routes.offerProcessor.routes;


import com.google.api.services.calendar.model.Event;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


/**
 * Created by mionisation on 6/9/16.
 */
//@Component
public class CreateGoogleCalendarRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        Event event = new Event();
        String e = "New construction is happening";
        from("direct:createGoogleCalendar").end();
                //TODO: authentification
                // TODO acl or calendars endpoint?,
                // TODO insert or update (which endpoint) new dates with com.google.api.services.calendar.model.Calendar
        //.to("google-calendar://events/quickAdd?calendarId=mycalendar&text=e");
    }
}
