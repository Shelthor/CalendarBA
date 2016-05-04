package com.calendarba.controller;

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

@Controller
public class EventController {

    private EventService EventService;

    @Autowired(required=true)
    @Qualifier(value="eventService")
    public void setEventService(EventService ps){
        this.EventService = ps;
    }

    @RequestMapping(value = "/Events", method = RequestMethod.GET)
    public String listEvents(Model model) {
        model.addAttribute("Event", new Event());
        model.addAttribute("listEvents", this.EventService.listEvents());
        return "Event";
    }

    //For add and update Event both
    @RequestMapping(value= "/Event/add", method = RequestMethod.POST)
    public String addEvent(@ModelAttribute("Event") Event p){

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

}