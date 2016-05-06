package com.calendarba.controller;


        import org.apache.log4j.Logger;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Qualifier;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;
        import com.calendarba.model.Event;
        import com.calendarba.service.EventService;

        import java.lang.reflect.Field;

@Controller
public class EventController {

    private EventService EventService;

    @Autowired(required=true)
    @Qualifier(value="eventService")
    public void setEventService(EventService ps){
        this.EventService = ps;
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String listEvents(Model model) {

        model.addAttribute("Event", new Event());
        model.addAttribute("listEvents", this.EventService.listEvents());
        return "Event";
    }

    //For add and update Event both
    @RequestMapping(value= "/event/add", method = RequestMethod.POST)
    public String addEvent(@ModelAttribute("Event") Event event){
        PrintObject(event, Logger.getRootLogger());
        if(event.getEventId() == 0){
            //new Event, add it
            this.EventService.addEvent(event);
        }else{
            //existing Event, call update
            this.EventService.updateEvent(event);
        }
        return "redirect:/events";
    }

    @RequestMapping(value= "/event/add/validate", method = RequestMethod.POST)
    public String addValidateEvent(@RequestBody Event event){
        PrintObject(event, Logger.getRootLogger());
        event.getEventName();

        return "/events";
    }


    @RequestMapping("/remove/{id}")
    public String removeEvent(@PathVariable("id") int id){

        this.EventService.removeEvent(id);
        return "redirect:/events";
    }

    @RequestMapping("/edit/{id}")
    public String editEvent(@PathVariable("id") int id, Model model){

        PrintObject(this.EventService.getEventById(id), Logger.getRootLogger());
        model.addAttribute("Event", this.EventService.getEventById(id));
        model.addAttribute("listEvents", this.EventService.listEvents());
        return "Event";
    }

    /**
     * Print all Fields from a specific Object
     *
     * @param obj The Object to print
     * @param log The logger to use for output
     * @author Steffen
     */

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