package com.calendarba.service;

import com.calendarba.model.Event;

import java.util.List;

public interface EventDAO {

        public Boolean addEvent(Event p);
        public void updateEvent(Event p);
        public Event getEventById(int id);
        public void removeEvent(int id);
        public List<Event> listEvents();

}

