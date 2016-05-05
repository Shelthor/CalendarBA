package com.calendarba.service;


import com.calendarba.model.Category;

import java.util.List;

/**
 * Created by prans on 02.05.2016.
 */
public interface CategoryService {

    public void addCategory(Category p);
    public void updateCategory(Category p);
    public Category getCategoryById(int id);
    public void removeCategory(int id);
    public List<Category> listCategorys();

}
