package io.nirahtech.ride4ever.domain.authentication;

import java.io.Serializable;

public final class Credential implements Serializable {
    public String username;
    public String password;

    public Credential() {

    }

    public Credential(final String username, final String password) {
        this.username = username;
        this.password = password;
    }
    
    public final String getUsername() {
        return this.username;
    }
    public final void setUsername(final String username) {
        this.username = username;
    }

    public final String getPassword() {
        return this.password;
    }
    public final void setPassword(final String password) {
        this.password = password;
    }


}
