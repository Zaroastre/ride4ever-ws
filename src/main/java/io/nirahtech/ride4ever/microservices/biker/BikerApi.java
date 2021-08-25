package io.nirahtech.ride4ever.microservices.biker;

import java.util.List;

import io.nirahtech.ride4ever.core.environment.Biker;
import io.nirahtech.ride4ever.infrastructure.api.Crud;

interface BikerApi extends Crud<Integer,  Biker> {
    List<Biker> findAll();
    Biker findByEmail(final String email);
}
