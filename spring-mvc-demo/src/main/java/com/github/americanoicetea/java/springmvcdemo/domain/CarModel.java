package com.github.americanoicetea.java.springmvcdemo.domain;

import java.time.Year;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

public class CarModel {

    @NotBlank(message = "brand is mandatory")
    private String brand;
    @NotBlank(message = "Model is mandatory")
    private String model;
    @PastOrPresent(message = "Year cannot be future")
    private Year year;
    @NotNull(message = "power is mandatory")
    private Integer power;
    @NotNull(message = "energyCapacity is mandatory")
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
