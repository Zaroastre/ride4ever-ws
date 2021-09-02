/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.registration;

import io.nirahtech.ride4ever.microservices.biker.Biker;

interface RegistrationApi {
    Biker create(final Biker biker) throws RuntimeException;
    Biker find(final String email) throws RuntimeException;
    Biker update(final Biker biker) throws RuntimeException;
    Biker delete(final Biker biker) throws RuntimeException;
}
