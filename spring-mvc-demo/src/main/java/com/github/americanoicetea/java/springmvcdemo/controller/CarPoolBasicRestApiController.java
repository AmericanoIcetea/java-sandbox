package com.github.americanoicetea.java.springmvcdemo.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.americanoicetea.java.springmvcdemo.domain.CarModel;
import com.github.americanoicetea.java.springmvcdemo.service.CarPoolService;

/**
 * Basic use in rest api controller
 * consists of GET POST PUT DELETE PATCh HEAD examples
 * 
 */
@RestController
public class CarPoolBasicRestApiController {

    @Autowired
    private CarPoolService carPoolService;

    /**
     * get info of /car-model path api which http methods are allowed
     *
     * @return http content negotiation
     */
    @RequestMapping(value = "/car-model", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> carModelOptions() {
        return ResponseEntity
                .ok()
                .allow(HttpMethod.PUT, HttpMethod.POST, HttpMethod.PATCH, HttpMethod.OPTIONS)
                .build();
    }

    /**
     * get info of /car-models path api which http methods are allowed
     *
     * @return http content negotiation
     */
    @RequestMapping(value = "/car-models", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> carModelsOptions() {
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.OPTIONS)
                .build();
    }

    /**
     * get info of /car-model/{brand}/{model} path api which http methods are
     * allowed
     *
     * @return http content negotiation
     */
    @RequestMapping(value = "/car-models/{brand}", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> brandCarModelsOptions() {
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.OPTIONS)
                .build();
    }

    /**
     * get only header car model api
     *
     * @param brand
     * @param model
     * @return http content negotiation
     */
    @RequestMapping(value = "/car-models/{brand}/{model}", method = RequestMethod.HEAD)
    public ResponseEntity<CarModel> headCarModels(@PathVariable String brand, @PathVariable String model) {
        var body = carPoolService.getCarModel(brand, model);
        ResponseEntity<CarModel> response;
        if (body == null) {
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            response = new ResponseEntity<>(body, HttpStatus.OK);
        }
        return response;
    }

    /**
     * get only header car model api
     *
     * @param brand
     * @param model
     * @return http content negotiation
     */
    @RequestMapping(value = "/car-models/{brand}", method = RequestMethod.HEAD)
    public ResponseEntity<Collection<CarModel>> headBrandCarModels(String brand) {
        var body = carPoolService.getCarModels(brand);
        if (body == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(body, HttpStatus.OK);
        }
    }

    /**
     * get only header car model api
     *
     * @param brand
     * @param model
     * @return http content negotiation
     */
    @RequestMapping(value = "/car-models", method = RequestMethod.HEAD)
    public ResponseEntity<Collection<CarModel>> headCarModels() {
        var body = carPoolService.getCarModels();
        if (body == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(body, HttpStatus.OK);
        }
    }

    /**
     * get car info api
     *
     * @param car model brand
     * @return collection of car model
     */
    @GetMapping(value = "/car-models")
    public ResponseEntity<Collection<CarModel>> getCarModels() {
        var body = carPoolService.getCarModels();
        if (body == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(body, HttpStatus.OK);
        }
    }

    /**
     * get car info api
     *
     * @param car model brand
     * @return collection of car model
     */
    @GetMapping(value = "/car-models/{brand}")
    public ResponseEntity<Collection<CarModel>> getBrandCarModels(@PathVariable String brand) {
        var body = carPoolService.getCarModels(brand);
        if (body == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(body, HttpStatus.OK);
        }
    }

    /**
     * get car model api
     *
     * @param car brand
     * @param car model
     * @return car model
     */
    @GetMapping(value = "/car-model/{brand}/{model}")
    public ResponseEntity<CarModel> getCarModel(@PathVariable String brand, @PathVariable String model) {
        var body = carPoolService.getCarModel(brand, model);
        if (body == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(body, HttpStatus.OK);
        }
    }

    /**
     * create car model api
     *
     * @param body of car
     * @return body which has saved
     */
    @PostMapping(value = "/car-model")
    public ResponseEntity<CarModel> createCarModel(@RequestBody CarModel carModel) {
        return new ResponseEntity<>(carPoolService.createCar(carModel), HttpStatus.CREATED);
    }

    /**
     * update car model api
     * if exist create
     *
     * @param body of car
     * @return body which has saved
     */
    @PutMapping(value = "/car-model")
    public ResponseEntity<Object> putCarModel(@RequestBody CarModel carModel) {
        var body = carPoolService.updateCar(carModel);
        if (body == null) {
            body = carPoolService.createCar(carModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(body);
        } else {
            return new ResponseEntity<>(body, HttpStatus.OK);
        }
    }

    /**
     * delete car model api
     *
     * @param brand
     * @param model
     * @return body which has saved
     */
    @DeleteMapping(value = "/car-model/{brand}/{model}")
    public ResponseEntity<CarModel> deleteCarModel(@PathVariable String brand, @PathVariable String model) {
        var body = carPoolService.deleteCarModel(brand, model);
        if (body == null) {
            return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(body, HttpStatus.OK);
        }
    }

    /**
     * update partial car model api
     *
     * @param body of car
     * @return body which has saved
     */
    @PatchMapping(value = "/car-model")
    public ResponseEntity<CarModel> patchCarModel(@RequestBody CarModel carModel) {
        var body = carPoolService.updatePartialCarModel(carModel);
        ResponseEntity<CarModel> response = null;
        if (body == null) {
            return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(body, HttpStatus.OK);
        }
    }

}