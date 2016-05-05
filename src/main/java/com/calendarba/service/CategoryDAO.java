package com.calendarba.service;

/**
 * Created by prans on 02.05.2016.
 */


import com.calendarba.model.Category;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import java.util.List;

public interface CategoryDAO {

    public void addCategory(Category p);
    public void updateCategory(Category p);
    public Category getCategoryById(int id);
    public void removeCategory(int id);
    public List<Category> listCategorys();

}
