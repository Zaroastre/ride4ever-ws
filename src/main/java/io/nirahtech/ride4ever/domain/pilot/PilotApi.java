package io.nirahtech.ride4ever.domain.pilot;

import java.util.List;

import io.nirahtech.ride4ever.core.api.Crud;
import io.nirahtech.ride4ever.core.environment.Pilot;

interface PilotApi extends Crud<Integer,  Pilot> {
    List<Pilot> findAll();
    Pilot findByEmail(final String email);
}
