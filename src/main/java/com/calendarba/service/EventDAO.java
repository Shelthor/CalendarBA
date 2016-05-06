package com.calendarba.service;

/**
 * Created by prans on 02.05.2016.
 */


import com.calendarba.model.Event;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import java.util.List;

public interface EventDAO {

        public Boolean addEvent(Event p);
        public void updateEvent(Event p);
        public Event getEventById(int id);
        public void removeEvent(int id);
        public List<Event> listEvents();

}

