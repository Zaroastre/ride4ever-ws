/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.weather;

import java.util.List;

import io.nirahtech.ride4ever.infrastructure.api.Crud;

interface WeatherApi extends Crud<Integer,  Weather> {

    /**
     * 
     * @return
     */
    List<Weather> findAll();
}
