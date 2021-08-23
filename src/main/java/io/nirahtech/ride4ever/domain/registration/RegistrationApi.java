package io.nirahtech.ride4ever.domain.registration;

import io.nirahtech.ride4ever.core.environment.Biker;

interface RegistrationApi {
    Biker create(final Biker pilot) throws RuntimeException;
    Biker find(final String email) throws RuntimeException;
    Biker update(final Biker pilot) throws RuntimeException;
    Biker delete(final Biker pilot) throws RuntimeException;
}
