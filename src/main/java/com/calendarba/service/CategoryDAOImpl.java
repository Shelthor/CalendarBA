package com.calendarba.service;

import com.calendarba.model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;


@Repository
public class CategoryDAOImpl implements CategoryDAO {

    private SessionFactory sessionFactory;
    private static final Logger logger = LoggerFactory.getLogger(CategoryDAOImpl.class);

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public void addCategory(Category p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(p);
    }

    @Override
    public void updateCategory(Category p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(p);
    }


    @Override
    public Category getCategoryById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Category p = (Category) session.get(Category.class, new Integer(id));
        return p;
    }

    @Override
    public void removeCategory(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Category p = (Category) session.get(Category.class, new Integer(id));
        if(null != p){
            session.delete(p);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Category> listCategorys() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Category> CategorysList = session.createQuery("from Category").list();
        for(Category Category : CategorysList){
            logger.info("Category List::"+ Category);
        }
        return CategorysList;
    }
}
