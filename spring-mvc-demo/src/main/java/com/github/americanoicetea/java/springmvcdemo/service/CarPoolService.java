package com.github.americanoicetea.java.springmvcdemo.service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.github.americanoicetea.java.springmvcdemo.domain.CarModel;

@Service
public class CarPoolService {

    private final Map<String, Map<String, CarModel>> BRAND_CAR_POOL = new HashMap<>();

    public CarModel getCarModel(String brand, String model) {
        CarModel carModel = null;
        var brandPool = BRAND_CAR_POOL.get(brand);
        if (brandPool != null) {
            carModel = brandPool.get(model);
        }
        return carModel;
    }

    public Collection<CarModel> getCarModels() {
        var carModels = BRAND_CAR_POOL.values().stream().flatMap(col -> col.values().stream())
                .collect(Collectors.toUnmodifiableList());
        return carModels.isEmpty() ? null : carModels;
    }

    public Collection<CarModel> getCarModels(String brand) {
        var brandPool = BRAND_CAR_POOL.get(brand);
        return brandPool == null ? null : Collections.unmodifiableCollection(brandPool.values());
    }

    public CarModel createCar(CarModel carModel) {
        var brandPool = BRAND_CAR_POOL.get(carModel.getBrand());
        if (brandPool == null) {
            brandPool = new HashMap<>();
            BRAND_CAR_POOL.put(carModel.getBrand(), brandPool);
        }
        var inPoolCarModel = brandPool.get(carModel.getBrand());
        if (inPoolCarModel == null) {
            brandPool.put(carModel.getModel(), carModel);
            inPoolCarModel = brandPool.get(carModel.getModel());
        }
        return inPoolCarModel;
    }

    public CarModel updateCar(CarModel carModel) {
        var brandPool = BRAND_CAR_POOL.get(carModel.getBrand());
        CarModel inPoolCarModel = null;
        if (brandPool != null) {
            inPoolCarModel = brandPool.get(carModel.getModel());
            if (inPoolCarModel != null) {
                inPoolCarModel.setDescription(carModel.getDescription());
                inPoolCarModel.setPower(carModel.getPower());
                inPoolCarModel.setYear(carModel.getYear());
                inPoolCarModel.setEnergyCapacity(carModel.getEnergyCapacity());
            }
        }
        return inPoolCarModel;
    }

    public CarModel updatePartialCarModel(CarModel carModel) {
        Assert.isTrue(carModel.getBrand() != null || !carModel.getBrand().isEmpty(), "brand is mandatory");
        Assert.isTrue(carModel.getModel() != null || !carModel.getModel().isEmpty(), "model is mandatory");
        var brandPool = BRAND_CAR_POOL.get(carModel.getBrand());
        CarModel inPoolCarModel = null;
        if (brandPool != null) {
            inPoolCarModel = brandPool.get(carModel.getModel());
            if (inPoolCarModel != null) {
                if (carModel.getDescription() != null) {
                    inPoolCarModel.setDescription(carModel.getDescription());
                }
                if (carModel.getPower() != null) {
                    inPoolCarModel.setPower(carModel.getPower());
                }
                if (carModel.getYear() != null) {
                    inPoolCarModel.setYear(carModel.getYear());
                }
                if (carModel.getEnergyCapacity() != null) {
                    inPoolCarModel.setEnergyCapacity(carModel.getEnergyCapacity());
                }
            }
        }
        return inPoolCarModel;
    }

    public CarModel deleteCarModel(String brand, String model) {
        var brandPool = BRAND_CAR_POOL.get(brand);
        CarModel carModel = null;
        if (brandPool != null) {
            carModel = brandPool.remove(model);
        }
        return carModel;
    }
}
