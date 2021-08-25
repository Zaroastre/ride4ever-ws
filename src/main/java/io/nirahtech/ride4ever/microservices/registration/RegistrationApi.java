package io.nirahtech.ride4ever.microservices.registration;

import io.nirahtech.ride4ever.core.environment.Biker;

interface RegistrationApi {
    Biker create(final Biker biker) throws RuntimeException;
    Biker find(final String email) throws RuntimeException;
    Biker update(final Biker biker) throws RuntimeException;
    Biker delete(final Biker biker) throws RuntimeException;
}
