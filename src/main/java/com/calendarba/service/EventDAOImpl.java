package com.calendarba.service;

import com.calendarba.model.Event;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;


@Repository
public class EventDAOImpl implements EventDAO {

    private SessionFactory sessionFactory;
    private static final Logger logger = LoggerFactory.getLogger(EventDAOImpl.class);

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public Boolean addEvent(Event event) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Event> eventsList = session.createQuery("from Event").list();
        Boolean correctnessSwitch = true;
        for(int i=0; i < eventsList.size(); i++){
            Event testEvent = eventsList.get(i);
            if(     testEvent.getEventStart().compareTo(event.getEventStart())>=0 && testEvent.getEventEnd().compareTo(event.getEventEnd())<=0   ||
                    testEvent.getEventStart().compareTo(event.getEventStart())<=0 && testEvent.getEventEnd().compareTo(event.getEventEnd())>=0   ||
                    testEvent.getEventEnd().compareTo(event.getEventStart())>=0 && testEvent.getEventEnd().compareTo(event.getEventEnd())<=0     ||
                    testEvent.getEventStart().compareTo(event.getEventStart())>=0 && testEvent.getEventStart().compareTo(event.getEventEnd())<=0 ||
                    event.getEventStart().compareTo(event.getEventEnd())>0) {
                correctnessSwitch = false;
            }
        }
        if(correctnessSwitch){
            session.persist(event);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void updateEvent(Event event) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(event);
    }


    @Override
    public Event getEventById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Event event = (Event) session.get(Event.class, new Integer(id));
        return event;
    }

    @Override
    public void removeEvent(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Event event = (Event) session.get(Event.class, new Integer(id));
        if(null != event){
            session.delete(event);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Event> listEvents() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Event> eventsList = session.createQuery("from Event").list();
        for(Event event : eventsList){
            logger.info("Event List::"+ event);
        }
        return eventsList;
    }
}