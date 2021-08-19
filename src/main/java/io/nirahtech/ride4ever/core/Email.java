package io.nirahtech.ride4ever.core;

import java.io.Serializable;

public final class Email implements Serializable {
    private String username;
    private String domain;

    public Email() {

    }

    public Email(final String username, final String domain) {
        this.username = username;
        this.domain = domain;
    }

    public String getDomain() {
        return this.domain;
    }
    public String getUsername() {
        return this.username;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return String.format("%s@%s", this.username, this.domain);
    }

}
