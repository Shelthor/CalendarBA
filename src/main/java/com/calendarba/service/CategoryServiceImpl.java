package com.calendarba.service;

import com.calendarba.model.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by prans on 02.05.2016.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryDAO categoryDAO;

    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    @Transactional
    public void addCategory(Category p) {
        this.categoryDAO.addCategory(p);
    }

    @Override
    @Transactional
    public void updateCategory(Category p) {
        this.categoryDAO.updateCategory(p);
    }

    @Override
    @Transactional
    public Category getCategoryById(int id) {
        return this.categoryDAO.getCategoryById(id);
    }

    @Override
    @Transactional
    public void removeCategory(int id) {
        this.categoryDAO.removeCategory(id);
    }

    @Override
    @Transactional
    public List<Category> listCategorys() {
        return this.categoryDAO.listCategorys();
    }
}
