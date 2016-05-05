package com.calendarba.model;


import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;
import java.util.GregorianCalendar;

@Entity
@Table(name="event")
public class Event {
    @NotEmpty
    @Id
    @Column(name="Event_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int eventId;
    @Column(name="Event_name")
    private String eventName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name="Event_start")
    private Date eventStart;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name="Event_end")
    private Date eventEnd;
    @Column(name="Category_id")
    private int cId;

    public Event(){}

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventStart() {
        return eventStart;
    }

    public void setEventStart(Date eventStart) {
        this.eventStart = eventStart;
    }

    public Date getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(Date eventEnd) {
        this.eventEnd = eventEnd;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }
}
