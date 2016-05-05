package com.calendarba.controller;

import com.calendarba.model.Category;
import com.calendarba.model.Event;
import com.calendarba.service.CategoryService;
import com.calendarba.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by prans on 05.05.2016.
 */
@Controller
public class CategoryController {

    private com.calendarba.service.CategoryService categoryService;

    @Autowired(required = true)
    @Qualifier(value = "categoryService")
    public void setCategoryService(CategoryService ps) {
        this.categoryService = ps;
    }

    @RequestMapping(value = "/categorys", method = RequestMethod.GET)
    public String listEvents(Model model) {
        model.addAttribute("Category", new Category());
        model.addAttribute("listEvents", this.categoryService.listCategorys());
        return "Category";
    }
}