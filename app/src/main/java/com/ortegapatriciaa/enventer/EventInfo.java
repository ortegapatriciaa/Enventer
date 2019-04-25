package com.ortegapatriciaa.enventer;


import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by ortegapatriciaa on 9/9/2017.
 */

public class EventInfo {

    String eventId;
    String eventTitle;
    String dateStart;
    String timeStart;
    String dateEnd;
    String timeEnd;
    String location;
    String description;

    public EventInfo(){

    }

    public EventInfo(String eventId, String eventTitle, String dateStart, String timeStart, String dateEnd, String timeEnd, String location, String description) {
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.dateStart = dateStart;
        this.timeStart = timeStart;
        this.dateEnd = dateEnd;
        this.timeEnd = timeEnd;
        this.location = location;
        this.description = description;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
