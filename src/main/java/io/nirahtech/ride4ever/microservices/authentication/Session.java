package io.nirahtech.ride4ever.microservices.authentication;

import java.io.Serializable;
import java.util.UUID;

import io.nirahtech.ride4ever.microservices.biker.Biker;

final class Session implements Serializable {
    private String id = UUID.randomUUID().toString();
    private Biker biker;
    private String jwt;

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

    public String getId() {
        return id;
    }
    public void setBiker(Biker biker) {
        this.biker = biker;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

}
