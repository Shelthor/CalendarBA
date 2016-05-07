package com.calendarba.service;

/**
 * Created by Andreas Geißler on 06.05.2016.
 */
    import java.text.ParseException;
    import java.util.*;

    import org.springframework.format.annotation.DateTimeFormat;
    import org.xml.sax.Attributes;
    import org.xml.sax.SAXException;
    import org.xml.sax.helpers.DefaultHandler;
    import com.calendarba.model.Event;
    import com.calendarba.model.Category;
    import java.util.Date;
    import java.text.SimpleDateFormat;

    public class EventXMLHandler extends DefaultHandler {

        //List to hold event object
        private List<Event> eventList = null;
        private Event event = null;

        //Category gedöhns
        /*
        //List to hold category object
        private List<Category> categoryList = null;
        private Category category = null;
*/
        //getter method for event list
        public List<Event> getEventList() {
            return eventList;
        }
/*
        //getter method for category list
        public List<Category> getCategoryList(){
            return categoryList;
        }
*/
        boolean bEventStart = false;
        boolean bEventEnd = false;
        boolean bEventName = false;
        boolean bCategoryId = false;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes)
                throws SAXException {

            if (qName.equalsIgnoreCase("event")) {
                //create a new event and put it in Map
                String id = attributes.getValue("id");
                //initialize event object and set id attribute
                event = new Event();
              //  event.setId(Integer.parseInt(id));
                //initialize list
                if (eventList == null)
                    // Im Beispiel ist <> leer, wenn irgendwas nicht klappt, hier anfangen mit bugfixen
                    eventList = new ArrayList<Event>();
                    //categoryList = new ArrayList<Category>();
            } else if (qName.equalsIgnoreCase("EventStart")) {
                //set boolean values for fields, will be used in setting event variables
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
