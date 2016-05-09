package com.calendarba.service;

    import com.calendarba.model.Event;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

    public class EventXMLHandler extends DefaultHandler {

        //List to hold event object
        private List<Event> eventList = null;
        private Event event = null;

        //getter method for event list
        public List<Event> getEventList() {
            return eventList;
        }

        boolean bEventStart = false;
        boolean bEventEnd = false;
        boolean bEventName = false;
        boolean bCategoryId = false;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes)
                throws SAXException {

            if (qName.equalsIgnoreCase("event")) {
                //initialize event object and set id attribute
                event = new Event();
                //initialize list
                if (eventList == null)

                    eventList = new ArrayList<Event>();
            } else if (qName.equalsIgnoreCase("EventStart")) {
                bEventStart = true;
            } else if (qName.equalsIgnoreCase("EventEnd")) {
                bEventEnd = true;
            } else if (qName.equalsIgnoreCase("EventName")) {
                bEventName = true;
            } else if (qName.equalsIgnoreCase("CategoryId")) {
                bCategoryId = true;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equalsIgnoreCase("event")) {
                //add event object to list
                eventList.add(event);
            }
        }

        @Override
        public void characters(char ch[], int start, int length) throws SAXException {

            if (bEventStart) {
                //EventStart element, set event EventStart
                String dateString = new String(ch, start, length);
                SimpleDateFormat sdfEventStart = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMAN);
                try{
                    Date parsedEventStart = sdfEventStart.parse(dateString);
                    event.setEventStart(parsedEventStart);
                } catch (ParseException e){
                    e.printStackTrace();
                }
                bEventStart = false;
            } else if (bEventEnd) {
                String dateString = new String(ch, start, length);
                SimpleDateFormat sdfEventEnd = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMAN);
                try{
                    Date parsedEventEnd = sdfEventEnd.parse(dateString);
                    event.setEventEnd(parsedEventEnd);
                } catch (ParseException e){
                    e.printStackTrace();
                }
                bEventEnd = false;
            } else if (bEventName) {
                event.setEventName(new String(ch, start, length));
                bEventName = false;
            } else if (bCategoryId) {
                event.setCategoryId(Integer.parseInt(new String(ch, start, length)));
                bCategoryId = false;
            }
        }
    }
