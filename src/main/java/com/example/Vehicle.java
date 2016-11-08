package com.example;

import java.io.Serializable;

/**
 * Created by Ares on 11/5/2016.
 */
public class Vehicle implements Serializable{

    private int id;
    private String makeModel;
    private int year;
    private double retailPrice;

    public Vehicle() {
        this.id = 0;
        this.makeModel = "";
        this.year = 0;
        this.retailPrice = 0.0;
    }

    public Vehicle (int id, String makeModel, int year, double retailPrice) {
        this.id = id;
        this.makeModel = makeModel;
        this.year = year;
        this.retailPrice = retailPrice;
    }

    public String toString() {
        return this.getId() + ", " + this.makeModel + ", Year: " + this.year
                + ", Price: " + this.retailPrice;
    }

    public int getId() {return id;}
    public String getMakeModel() {return makeModel;}
    public void setMakeModel(String makeModel) {this.makeModel = makeModel;}
    public int getYear() {return year;}
    public void setYear(int year) {this.year = year;}
    public double getRetailPrice() {return retailPrice;}
    public void setRetailPrice(double retailPrice) {this.retailPrice = retailPrice;}
}