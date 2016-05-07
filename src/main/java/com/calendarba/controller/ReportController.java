package com.calendarba.controller;

import com.calendarba.model.Category;
import com.calendarba.model.Event;
import com.calendarba.service.CategoryService;
import com.calendarba.service.EventService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by prans on 06.05.2016.
 */
@Controller
public class ReportController {

    private com.calendarba.service.EventService eventService;
    private CategoryService categoryService;

    @Autowired(required=true)
    @Qualifier(value="eventService")
    public void setEventService(EventService es) {
        this.eventService = es;
    }

    @Autowired(required=true)
    @Qualifier(value="categoryService")
    public void setEventService(CategoryService cs) {
        this.categoryService = cs;
    }

    @RequestMapping(value = "/reports", method = RequestMethod.GET)
    public String listEvents(Model model) {
        //PrintObject(this.categoryService.listCategorys(), Logger.getRootLogger());
        List<Event> eventListOriginal =  this.eventService.listEvents();
        ArrayList<String> resultList = new ArrayList<String>();
        for(int i =0; i<eventListOriginal.size(); i++){
            String eventName = eventListOriginal.get(i).getEventName();
            for(int j = 0; j < resultList.size(); j++){
                if(resultList.get(j) == eventName)
            }
        }

        model.addAttribute("categorysList", this.categoryService.listCategorys());

        return "Reports";
    }

    @RequestMapping(value = "/report/sum/{id}", method = RequestMethod.GET)
    public String sumEvents(@PathVariable("id") int id, Model model) {
        List<Event> eventListOriginal =  this.eventService.listEvents();
        List<Event> eventList = new ArrayList<Event>();
        long sum =0;
        for(int i =0; i<eventListOriginal.size(); i++){
            if(eventListOriginal.get(i).getCategoryId() == id){
                eventList.add(eventListOriginal.get(i));
                PrintObject(eventListOriginal.get(i), Logger.getRootLogger());
            }
            sum += (eventListOriginal.get(i).getEventEnd().getTime() -
                    eventListOriginal.get(i).getEventStart().getTime());
        }
        long sumSeconds = sum / 1000;
        model.addAttribute("sumSeconds", sumSeconds);
        long sumMinutes = sum / (60 * 1000);
        model.addAttribute("sumMinutes", sumMinutes);
        long sumHours = sum / (60 * 60 * 1000);
        model.addAttribute("sumHours", sumHours);
        model.addAttribute("eventList",eventList);
        return "Result";
    }


    public void PrintObject(Object obj, Logger log)
    {
        Field[] fields = obj.getClass().getDeclaredFields();
        log.info("---- Object from Type '" + obj.getClass().getSimpleName() + "' ----");
        for (Field field : fields)
        {
            field.setAccessible(true);
            String name = field.getName();
            try
            {
                Object value = field.get(obj);
                log.info(name + ": " + value);
            } catch (IllegalArgumentException e)
            {
                log.info(name + ": ERROR - Illegal Argument");
                e.printStackTrace();
            } catch (IllegalAccessException e)
            {
                log.info(name + ": ERROR - Illegal Access");
                e.printStackTrace();
            }
        }
        log.info("---- End Object from Type '" + obj.getClass().getSimpleName() + "' ----");
    }

}

