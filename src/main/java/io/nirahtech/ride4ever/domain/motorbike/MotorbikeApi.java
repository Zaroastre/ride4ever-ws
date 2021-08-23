package io.nirahtech.ride4ever.domain.motorbike;

import java.util.List;

import io.nirahtech.ride4ever.core.environment.Motorbike;
import io.nirahtech.ride4ever.infrastructure.api.Crud;

interface MotorbikeApi extends Crud<Integer,  Motorbike> {
    List<Motorbike> findAll();
    Motorbike findByLicensePlate(final String licensePlate);
}
