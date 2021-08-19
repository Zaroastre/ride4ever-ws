package io.nirahtech.ride4ever.domain.pilot;

import java.util.List;

import io.nirahtech.ride4ever.core.environment.Pilot;
import io.nirahtech.ride4ever.infrastructure.api.Crud;

interface PilotApi extends Crud<Integer,  Pilot> {
    List<Pilot> findAll();
    Pilot findByEmail(final String email);
}
