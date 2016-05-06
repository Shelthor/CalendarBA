package com.calendarba.controller;


        import com.calendarba.service.CategoryService;
        import org.apache.log4j.Logger;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Qualifier;
        import org.springframework.beans.propertyeditors.CustomDateEditor;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.WebDataBinder;
        import org.springframework.web.bind.annotation.*;
        import com.calendarba.model.Event;
        import com.calendarba.service.EventService;

        import java.lang.reflect.Field;
        import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.util.Date;

@Controller
public class EventController {

    private EventService EventService;
    private CategoryService categoryService;

    @Autowired(required=true)
    @Qualifier(value="eventService")
    public void setEventService(EventService ps){
        this.EventService = ps;
    }

    @Autowired(required=true)
    @Qualifier(value="categoryService")
    public void setCategoryService(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @InitBinder
    public void customizeConversions(WebDataBinder binder) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        df.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(df, true));
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String listEvents(Model model) {
        model.addAttribute("Event", new Event());
        model.addAttribute("listEvents", this.EventService.listEvents());
       // model.addAttribute("listCategorys",this.categoryService.listCategorys());
        return "Event";
    }

    //For add and update Event both
    @RequestMapping(value= "/event/add", method = RequestMethod.POST)
    public String addEvent(@ModelAttribute("Event") Event event, Model model){
        PrintObject(event, Logger.getRootLogger());
        if(event.getEventId() == 0){
            //new Event, add it
            if(!this.EventService.addEvent(event))
            {model.addAttribute("addError", new String("could not add Event - 'cause of reasons")) ;}
        }else{
            //existing Event, call update
            this.EventService.updateEvent(event);
        }
        return "redirect:/events";
    }

    @RequestMapping("/remove/{id}")
    public String removeEvent(@PathVariable("id") int id){

        this.EventService.removeEvent(id);
        return "redirect:/events";
    }

    @RequestMapping("/edit/{id}")
    public String editEvent(@PathVariable("id") int id, Model model){
        model.addAttribute("Event", this.EventService.getEventById(id));
        model.addAttribute("listEvents", this.EventService.listEvents());
        return "Event";
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