package io.nirahtech.ride4ever.microservices.authentication;

interface AuthenticationApi {
    Session login(final Credential credential) throws RuntimeException;
    void logout(final String sessionID) throws RuntimeException;
}
