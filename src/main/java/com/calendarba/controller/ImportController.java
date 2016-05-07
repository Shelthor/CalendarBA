package com.calendarba.controller;

import com.calendarba.model.Category;
import com.calendarba.model.Event;
import com.calendarba.service.CategoryService;
import com.calendarba.service.EventService;
import com.calendarba.service.MyHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.calendarba.service.XMLParserSAX;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * Created by Andreas Geißler on 07.05.2016.
 */
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
            MyHandler handlerEvent = new MyHandler();
            saxParser.parse(new File("/upload/calendarimport.xml"), handlerEvent);

            //MyHandler handlerCategory = new MyHandler();
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

