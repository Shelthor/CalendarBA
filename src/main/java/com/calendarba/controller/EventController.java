package com.calendarba.controller;


        import org.apache.log4j.Logger;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Qualifier;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.ModelAttribute;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
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
    public String addEvent(@ModelAttribute("Event") Event p){
        PrintObject(p, Logger.getRootLogger());
        if(p.getId() == 0){
            //new Event, add it
            this.EventService.addEvent(p);
        }else{
            //existing Event, call update
            this.EventService.updateEvent(p);
        }

        return "redirect:/Events";

    }

    @RequestMapping("/remove/{id}")
    public String removeEvent(@PathVariable("id") int id){

        this.EventService.removeEvent(id);
        return "redirect:/Events";
    }

    @RequestMapping("/edit/{id}")
    public String editEvent(@PathVariable("id") int id, Model model){
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