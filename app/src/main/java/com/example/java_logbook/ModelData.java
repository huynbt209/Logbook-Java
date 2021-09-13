package com.example.java_logbook;

import android.graphics.ColorSpace;

import java.io.Serializable;

public class ModelData implements Serializable {

    private String name;
    private String propertyType;
    private String room;
    private String furnitureType;
    private String price;
    private String date;
    private String notes;

    public ModelData(){
        name = "";
        room = "";
        date = "";
        furnitureType = "";
        price = "";
        propertyType = "";
        notes = "";
    }
    //Get
    public String getName(){
        return name;
    }
    public String getPropertyType(){
        return propertyType;
    }
    public String getRoom(){
        return room;
    }
    public String getFurnitureType(){
        return furnitureType;
    }
    public String getDate(){
        return date;
    }
    public String getPrice(){
        return price;
    }
    public String getNotes(){
        return notes;
    }
    //Set
    public void setName(String name){
        this.name = name;
    }
    public void setPropertyType(String propertyType){
        this.propertyType = propertyType;
    }
    public void setRoom(String room){
        this.room = room;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setPrice(String price){
        this.price = price;
    }
    public void setNotes(String notes){
        this.notes = notes;
    }
    public void setFurnitureType(String furnitureType){
        this.furnitureType = furnitureType;
    }
}
