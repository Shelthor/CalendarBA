package com.calendarba.model;

import java.util.ArrayList;

public class CalendarMap {
    private ArrayList<Event> eventList;

    public ArrayList<Event> getEventList(){
        return this.eventList;
    }

    public CalendarMap(){
        this.eventList = new ArrayList<Event>();
    }

    public int addEventToList(Event event){
        if(this.eventList.add(event)) {
            return 1;
        }
        else return 0;
    }

    public int validate(Event event){ //TODO:hier sollte dann geprüft werden, ob ein spezielles Event zulässig ist, und in die Liste aufgenommen werden kann --> eindeutige id, keine zeitkonflikte zu anderen Events, alle Attribute gesetzt
        return 0;
    }

    public Event getEventByIndex(int id){
        return this.eventList.get(id);
    }
}
