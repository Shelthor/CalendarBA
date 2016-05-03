package com.calendarba.model;

import java.util.ArrayList;

public class CalendarMap {
    private ArrayList<Event> eventList;

    public ArrayList<Event> getEventList(){
        return this.eventList;
    }

    public CalendarMap(){
        this.eventList = new ArrayList<Event>();
    }

    public int addEventToList(Event event){
        if(this.eventList.add(event)) {
            return 1;
        }
        else return 0;
    }
    /*

     */
    public int validate(Event event){ //TODO:hier sollte dann geprüft werden, ob ein spezielles Event zulässig ist, und in die Liste aufgenommen werden kann --> eindeutige id, keine zeitkonflikte zu anderen Events, alle Attribute gesetzt
        return 0;
    }

    public  ArrayList<ArrayList<String>> eventListToHtml(){
        ArrayList<ArrayList<String>> array =  new ArrayList();
        for( int i= 0; i <  this.eventList.size(); i++){
            ArrayList<String> list = new ArrayList();
            list.add(0,this.eventList.get(i).getName());
            list.add(1,this.eventList.get(i).getStart().toString());
            list.add(2,this.eventList.get(i).getEnd().toString());
            list.add(3,this.eventList.get(i).getPrivat().toString());
            list.add(4,String.valueOf(this.eventList.get(i).getId()));
            array.add(i,list);
        }
        return array;
    }

    public Event getEventByIndex(int id){
        return this.eventList.get(id);
    }
}
