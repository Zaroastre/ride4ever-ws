package io.nirahtech.ride4ever.microservices.authentication;

import java.io.Serializable;

import io.nirahtech.ride4ever.microservices.biker.Biker;

final class Session implements Serializable {
    private final Biker biker;
    private final String jwt;

    public Session(final String jwt, final Biker biker) {
        this.jwt = jwt;
        this.biker = biker;
    }

    public String getJwt() {
        return jwt;
    }
    
    public Biker getBiker() {
        return biker;
    }

}
