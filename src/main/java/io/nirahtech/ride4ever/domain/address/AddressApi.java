package io.nirahtech.ride4ever.domain.address;

import java.util.List;

import io.nirahtech.ride4ever.core.environment.Address;
import io.nirahtech.ride4ever.infrastructure.api.Crud;

interface AddressApi extends Crud<Integer,  Address> {
    List<Address> findAll();
}