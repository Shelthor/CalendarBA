package com.calendarba.controller;

import com.calendarba.model.Category;
import com.calendarba.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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
        model.addAttribute("listCategorys", this.categoryService.listCategorys());
        return "Category";
    }

    @RequestMapping(value = "/category/add", method = RequestMethod.POST)
    public String addEvent(@ModelAttribute("Category") Category category) {

        if (category.getCategoryId() == 0) {
            //new Event, add it
            this.categoryService.addCategory(category);
        } else {
            //existing Event, call update
            this.categoryService.updateCategory(category);
        }
        return "redirect:/categorys";
    }

    @RequestMapping("/category/remove/{id}")
    public String removeCategory(@PathVariable("id") int id){

        this.categoryService.removeCategory(id);
        return "redirect:/categorys";
    }

    @RequestMapping("/category/edit/{id}")
    public String editEvent(@PathVariable("id") int id, Model model){
        model.addAttribute("Category", this.categoryService.getCategoryById(id));
        model.addAttribute("listCategorys", this.categoryService.listCategorys());
        return "Category";
    }
}