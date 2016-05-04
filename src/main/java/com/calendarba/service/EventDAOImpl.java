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
    //private static final Logger logger = LoggerFactory.getLogger(EventDAOImpl.class);

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public void addEvent(Event p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(p);
    }

    @Override
    public void updateEvent(Event p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(p);
    }


    @Override
    public Event getEventById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Event p = (Event) session.load(Event.class, new Integer(id));
        return p;
    }

    @Override
    public void removeEvent(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Event p = (Event) session.load(Event.class, new Integer(id));
        if(null != p){
            session.delete(p);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Event> listEvents() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Event> eventsList = session.createQuery("from Event").list();
        for(Event p : eventsList){
            //logger.info("Person List::"+p);
        }
        return eventsList;
    }
}
