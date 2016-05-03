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

        private EventDAO EventDAO;

        public void setEventDAO(EventDAO EventDAO) {
            this.EventDAO = EventDAO;
        }

        @Override
        @Transactional
        public void addEvent(Event p) {
            this.EventDAO.addEvent(p);
        }

        @Override
        @Transactional
        public void updateEvent(Event p) {
            this.EventDAO.updateEvent(p);
        }

        @Override
        @Transactional
        public Event getEventById(int id) {
            return this.EventDAO.getEventById(id);
        }

        @Override
        @Transactional
        public void removeEvent(int id) {
            this.EventDAO.removeEvent(id);
        }

        @Override
        @Transactional
        public List<Event> listEvents() {
            return this.EventDAO.listEvents();
        }
}
