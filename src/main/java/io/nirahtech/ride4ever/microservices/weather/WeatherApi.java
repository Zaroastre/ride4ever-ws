package io.nirahtech.ride4ever.microservices.weather;

import java.util.List;

import io.nirahtech.ride4ever.infrastructure.api.Crud;

interface WeatherApi extends Crud<Integer,  Weather> {
    List<Weather> findAll();
}
