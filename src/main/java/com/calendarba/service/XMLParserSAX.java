package com.calendarba.service;


        import com.calendarba.model.Event;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class XMLParserSAX {

    public  XMLParserSAX() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            EventXMLHandler handlerEvent = new EventXMLHandler();
            saxParser.parse(new File("/upload/calendarimport.xml"), handlerEvent);

            //EventXMLHandler handlerCategory = new EventXMLHandler();
            //saxParser.parse(new);

            //Get Employees list
            List<Event> eventList = handlerEvent.getEventList();
            //print employee information
            for(Event event : eventList)
                System.out.println(event);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }


    }

}