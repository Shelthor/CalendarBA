package com.calendarba.controller;

import com.calendarba.model.Event;
import com.calendarba.service.EventXMLHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class ImportController {

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String showImportView() {

        return "Import";
    }



    @RequestMapping(value = "/import/do", method = RequestMethod.POST)
    public String importXml(Model model) {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            EventXMLHandler handlerEvent = new EventXMLHandler();
            saxParser.parse(new File("/upload/calendarimport.xml"), handlerEvent);

            //EventXMLHandler handlerCategory = new EventXMLHandler();
            //saxParser.parse(new);
            model.addAttribute("eventList", handlerEvent.getEventList());
            //Get Employees list
            List<Event> eventList = handlerEvent.getEventList();
            //print employee information
            for(Event event : eventList)
                System.out.println(event);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return "Import";
    }

}

