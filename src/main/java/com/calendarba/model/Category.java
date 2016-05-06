package com.calendarba.model;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;

/**
 * Created by prans on 04.05.2016.
 */
@Entity
@Table(name="category")
public class Category {

    @Id
    @Column(name="Category_id")

    private int categoryId;
    @Column(name="Category_name")
    private String categoryName;
 /*   @ManyToOne
    @JoinColumn(name="ID_CATALOG")
    private Catalog catalog;   um als foreign key zu kennzeichnen*/
    @Column(name="Category_privat")
    private Boolean categoryPrivat;
    public Category(int categoryId, String categoryName, Boolean categoryPrivat){
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryPrivat = categoryPrivat;
    }
    public Category(){}


    public String getCategoryName(){return this.categoryName;}
    public Boolean getCategoryPrivat() {return this.categoryPrivat;}
    public int getCategoryId() {return this.categoryId;}


    public void generateId(){
        //TODO: per DB-Abfrage die niedrigste verfügbare (oder so) Id ermitteln und ausgeben
    }
    public void setCategoryId(int id){
        this.categoryId = id;
    }

    public void setCategoryPrivat(Boolean categoryPrivat) {
        this.categoryPrivat = categoryPrivat;
    }

    public void setCategoryName(String name) {
        this.categoryName = name;
    }



}
