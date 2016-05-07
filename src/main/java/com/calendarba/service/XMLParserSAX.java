package com.calendarba.service;

/**
 * Created by Andreas Geißler on 07.05.2016.
 */


        import java.io.File;
        import java.io.IOException;
        import java.util.List;

        import javax.xml.parsers.ParserConfigurationException;
        import javax.xml.parsers.SAXParser;
        import javax.xml.parsers.SAXParserFactory;

        import com.calendarba.model.Event;
        import org.xml.sax.SAXException;


public class XMLParserSAX {

    public  XMLParserSAX() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MyHandler handlerEvent = new MyHandler();
            saxParser.parse(new File("/upload/calendarimport.xml"), handlerEvent);

            //MyHandler handlerCategory = new MyHandler();
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