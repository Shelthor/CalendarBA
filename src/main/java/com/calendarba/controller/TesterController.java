package com.calendarba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Prans on 06.05.2016.
 */

@Controller
public class TesterController {
    @RequestMapping(value = "/tester", method = RequestMethod.GET)
    public String listEvents(Model model) {
    return "Tester";
    }
}