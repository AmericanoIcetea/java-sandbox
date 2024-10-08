package com.github.americanoicetea.java.springmvcdemo.domain;

import java.time.Year;

public class CarModel {

    private String brand;

    private String model;

    private Year year;
    
    private Integer power;
    
    private Integer energyCapacity;
    
    private String description;

    public CarModel() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getEnergyCapacity() {
        return energyCapacity;
    }

    public void setEnergyCapacity(Integer energyCapacity) {
        this.energyCapacity = energyCapacity;
    }
}
