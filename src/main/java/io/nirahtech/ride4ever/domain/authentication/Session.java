package io.nirahtech.ride4ever.domain.authentication;

import java.io.Serializable;

import io.nirahtech.ride4ever.core.environment.Pilot;

final class Session implements Serializable {
    private final Pilot pilot;
    private final String jwt;

    public Session(final String jwt, final Pilot pilot) {
        this.jwt = jwt;
        this.pilot = pilot;
    }

    public String getJwt() {
        return jwt;
    }
    
    public Pilot getPilot() {
        return pilot;
    }

}
