package com.github.americanoicetea.java.springmvcdemo.domain;

import java.time.Year;

public record CarModel (
    String brand,
    String model,
    Year year,
    Integer power,
    Integer energyCapacity,
    String description){

    public CarModel copy(CarModel newCarModel){
        return new CarModel(brand, model, newCarModel.year(), newCarModel.power(), newCarModel.energyCapacity, newCarModel.description());
    }

    public CarModel partialCopy(CarModel newCarModel){

        var newYear = newCarModel.year() == null ? year : newCarModel.year();
        var newPower = newCarModel.power() == null ? power : newCarModel.power();
        var newEnergyCapcity = newCarModel.energyCapacity() == null ? energyCapacity : newCarModel.energyCapacity();
        var newDescription = newCarModel.description() == null ? description : newCarModel.description();

        return new CarModel(brand, model, newYear, newPower, newEnergyCapcity, newDescription);
    }
}


