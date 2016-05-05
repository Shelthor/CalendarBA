package com.calendarba.service;

import com.calendarba.model.Event;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by prans on 02.05.2016.
 */
@Service
public class EventServiceImpl implements EventService {

        private EventDAO eventDAO;

        public void setEventDAO(EventDAO eventDAO) {
            this.eventDAO = eventDAO;
        }

        @Override
        @Transactional
        public void addEvent(Event p) {
            this.eventDAO.addEvent(p);
        }

        @Override
        @Transactional
        public void updateEvent(Event p) {
            this.eventDAO.updateEvent(p);
        }

        @Override
        @Transactional
        public Event getEventById(int id) {
            return this.eventDAO.getEventById(id);
        }

        @Override
        @Transactional
        public void removeEvent(int id) {
            this.eventDAO.removeEvent(id);
        }

        @Override
        @Transactional
        public List<Event> listEvents() {
            return this.eventDAO.listEvents();
        }
}
