package io.nirahtech.ride4ever.microservices.auth;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role implements Serializable {
    @Id
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Role() {

    }
    public Role(String name) {
        this.name = name;
    }
}
