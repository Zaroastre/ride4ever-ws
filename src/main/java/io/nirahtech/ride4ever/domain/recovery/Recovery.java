package io.nirahtech.ride4ever.domain.recovery;

import java.io.Serializable;

public class Recovery implements Serializable {
    private String identity;
    private String token;
    private String publisher;
    private String code;
    private String password;

    public Recovery() {

    }

    public String getIdentity() {
        return this.identity;
    }
    public String getToken() {
        return this.token;
    }
    public String getPublisher() {
        return this.publisher;
    }
    public String getCode() {
        return this.code;
    }
    public String getPassword() {
        return this.password;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public void setIdentity(String identity) {
        this.identity = identity;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public void setToken(String token) {
        this.token = token;
    }

}
