package io.nirahtech.ride4ever.microservices.address;

import java.util.List;

import io.nirahtech.ride4ever.infrastructure.api.Crud;

interface AddressApi extends Crud<Integer,  Address> {
    List<Address> findAll();
    String findAddressByCoordinates(final double latitude, final double longitude);
    String findWeatherByCoordinates(final double latitude, final double longitude);
}
