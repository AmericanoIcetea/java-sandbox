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
        var brandPool = BRAND_CAR_POOL.get(carModel.brand());
        if (brandPool == null) {
            brandPool = new HashMap<>();
            BRAND_CAR_POOL.put(carModel.brand(), brandPool);
        }
        var inPoolCarModel = brandPool.get(carModel.brand());
        if (inPoolCarModel == null) {
            brandPool.put(carModel.model(), carModel);
            inPoolCarModel = brandPool.get(carModel.model());
        }
        return inPoolCarModel;
    }

    public CarModel updateCar(CarModel carModel) {
        var brandPool = BRAND_CAR_POOL.get(carModel.brand());
        if (brandPool != null) {
            var inPoolCarModel = brandPool.get(carModel.model());
            if (inPoolCarModel != null) {
                var newCar = inPoolCarModel.copy(carModel);
                brandPool.replace(newCar.brand(), inPoolCarModel);
                return newCar;
            }
        }
        return null;
    }

    public CarModel updatePartialCarModel(CarModel carModel) {
        Assert.isTrue(carModel.brand() != null || !carModel.brand().isEmpty(), "brand is mandatory");
        Assert.isTrue(carModel.brand() != null || !carModel.brand().isEmpty(), "model is mandatory");
        var brandPool = BRAND_CAR_POOL.get(carModel.brand());
        if (brandPool != null) {
            var inPoolCarModel = brandPool.get(carModel.model());
            if (inPoolCarModel != null) {
                inPoolCarModel = inPoolCarModel.partialCopy(carModel);
                brandPool.replace(inPoolCarModel.brand(), inPoolCarModel);
                return inPoolCarModel;
            }
        }
        return null;
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
