package com.routes.offerProcessor.routes;

import org.apache.camel.builder.RouteBuilder;
import com.google.api.services.calendar.model.Calendar;
/**
 * Created by mionisation on 6/9/16.
 */
public class CreateGoogleCalendarRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        Calendar cal = new Calendar();
        from("direct:createGoogleCalendar")
                //TODO: authentification
                // TODO acl or calendars endpoint?,
                // TODO insert or update (which endpoint) new dates with com.google.api.services.calendar.model.Calendar
        .to("google-calendar://calendars/update?calendarId=mycalendar&content=GoogleCalendar");
    }
}
