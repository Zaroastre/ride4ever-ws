package io.nirahtech.ride4ever.domain.registration;

import io.nirahtech.ride4ever.core.environment.Pilot;

interface RegistrationApi {
    Pilot create(final Pilot pilot) throws RuntimeException;
    Pilot find(final String email) throws RuntimeException;
    Pilot update(final Pilot pilot) throws RuntimeException;
    Pilot delete(final Pilot pilot) throws RuntimeException;
}
