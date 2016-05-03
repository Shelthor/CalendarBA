package com.calendarba.service;


import com.calendarba.model.Event;

import java.util.List;

/**
 * Created by prans on 02.05.2016.
 */
public interface EventService {

        public void addEvent(Event p);
        public void updateEvent(Event p);
        public Event getEventById(int id);
        public void removeEvent(int id);
        public List<Event> listEvents();
    
}
