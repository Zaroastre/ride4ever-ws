package io.nirahtech.ride4ever.microservices.recovery;

import java.io.Serializable;

class Recovery implements Serializable {
    private String identity;
    private String token;
    private String publisher;
    private String code;
    private String password;

    Recovery() {

    }

    final String getIdentity() {
        return this.identity;
    }
    final String getToken() {
        return this.token;
    }
    final String getPublisher() {
        return this.publisher;
    }
    final String getCode() {
        return this.code;
    }
    final String getPassword() {
        return this.password;
    }

    final void setCode(String code) {
        this.code = code;
    }
    final void setIdentity(String identity) {
        this.identity = identity;
    }
    final void setPassword(String password) {
        this.password = password;
    }
    final void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    final void setToken(String token) {
        this.token = token;
    }

}
