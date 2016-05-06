package com.calendarba.service;

/**
 * Created by Andreas Geißler on 06.05.2016.
 */
    import java.util.*;

    import org.xml.sax.Attributes;
    import org.xml.sax.SAXException;
    import org.xml.sax.helpers.DefaultHandler;
    import com.calendarba.model.Event;
    import com.calendarba.model.Category;

    public class MyHandler extends DefaultHandler {

        //List to hold event object
        private List<Event> eventList = null;
        private Event event = null;
        //List to hold category object
        private List<Category> categoryList = null;
        private Category category = null;

        //getter method for event list
        public List<Event> getEventList() {
            return eventList;
        }

        //getter method for category list
        public List<Category> getCategoryList(){
            return categoryList;
        }

        boolean bEventStart = false;
        boolean bEventEnd = false;
        boolean bEventName = false;
        boolean bCategoryName = false;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes)
                throws SAXException {

            if (qName.equalsIgnoreCase("event")) {
                //create a new event and put it in Map
                String id = attributes.getValue("id");
                //initialize event object and set id attribute
                event = new Event();
                event.setId(Integer.parseInt(id));
                //initialize list
                if (eventList == null && categoryList == null)
                    // Im Beispiel ist <> leer, wenn irgendwas nicht klappt, hier anfangen mit bugfixen
                    eventList = new ArrayList<Event>();
                    categoryList = new ArrayList<Category>();
            } else if (qName.equalsIgnoreCase("EventStart")) {
                //set boolean values for fields, will be used in setting event variables
                bEventStart = true;
            } else if (qName.equalsIgnoreCase("EventEnd")) {
                bEventEnd = true;
            } else if (qName.equalsIgnoreCase("EventName")) {
                bEventName = true;
            } else if (qName.equalsIgnoreCase("CategoryName")) {
                bCategoryName = true;
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
                Calendar calEventStart = new GregorianCalendar()
                event.setEventStart(Date.parse(new String(ch, start, length)));
                bEventStart = false;
            } else if (bEventEnd) {
                event.setEventEnd(new (ch, start, length));
                bEventEnd = false;
            } else if (bEventName) {
                event.setEventName(new String(ch, start, length));
                bRole = false;
            } else if (bCategoryName) {
                category.setGender(new String(ch, start, length));
                bGender = false;
            }
        }
    }
