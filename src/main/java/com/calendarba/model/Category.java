package com.calendarba.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by prans on 04.05.2016.
 */
@Entity
@Table(name="category")
public class Category {
    @NotEmpty
    @Id
    @Column(name="Category_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int c_id;
    @Column(name="Category_name")
    private String name;
    @Column(name="Category_privat")
    private Boolean privat;
    public Category(int c_id, String name, Boolean privat){
        this.c_id = c_id;
        this.name = name;
        this.privat = privat;
    }
    public Category(){}


    public String getName(){return this.name;}
    public Boolean getPrivat() {return this.privat;}
    public int getId() {return this.c_id;}


    public void generateId(){
        //TODO: per DB-Abfrage die niedrigste verfügbare (oder so) Id ermitteln und ausgeben
    }
    public void setId(int c_id){
        this.c_id = c_id;
    }

    public void setPrivat(Boolean privat) {
        this.privat = privat;
    }

    public void setName(String name) {
        this.name = name;
    }



}
