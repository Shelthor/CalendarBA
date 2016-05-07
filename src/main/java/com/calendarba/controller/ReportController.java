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
        ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();
        for(int i =0; i<eventListOriginal.size(); i++){

            ArrayList<String> resultObject = new ArrayList<String>();
            String eventName = eventListOriginal.get(i).getEventName();
            Boolean nameFound = false;
            for(int j = 0; j < resultList.size(); j++){
                PrintObject(resultList.get(j).get(0), Logger.getRootLogger());
                if(resultList.get(j).get(0).equals(eventName)){
                    nameFound = true;
                }
            }
            if(!nameFound){
                resultObject.add(eventName);
                long sum = 0;
                for(int k =i; k < eventListOriginal.size(); k++){
                    if(eventListOriginal.get(k).getEventName().equals(eventName)){
                        sum +=    eventListOriginal.get(k).getEventEnd().getTime()
                                - eventListOriginal.get(k).getEventStart().getTime();
                    }
                }
                long resultDays = sum / (24 * 60 * 60 * 1000);
                resultObject.add(String.valueOf(resultDays));
                sum = sum % (24 * 60 * 60 * 1000);
                long resultHours = sum / (60 * 60 * 1000);
                resultObject.add(String.valueOf(resultHours));
                sum = sum % ( 60 * 60 * 1000);
                long resultMin = sum / (60 * 1000);
                resultObject.add(String.valueOf(resultMin));
                resultList.add(resultObject);
            }
        }
        model.addAttribute("resultList",resultList);
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

        long sumDays = sum / (24 * 60 * 60 * 1000);
        model.addAttribute("sumDays", sumDays);
        sum = sum % (24 * 60 * 60 * 1000);
        long sumHours = sum / (60 * 60 * 1000);
        model.addAttribute("sumHours", sumHours);
        if(sumHours != 0)sum = sum % (60 * 60 * 1000);
        long sumMinutes = sum / (60 * 1000);
        model.addAttribute("sumMin", sumMinutes);

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

