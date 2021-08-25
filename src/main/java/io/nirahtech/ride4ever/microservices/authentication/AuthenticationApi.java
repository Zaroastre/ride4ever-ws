package io.nirahtech.ride4ever.microservices.authentication;

interface AuthenticationApi {
    Session login(final Credential credential) throws RuntimeException;
    void logout(final Session jwt) throws RuntimeException;
}
