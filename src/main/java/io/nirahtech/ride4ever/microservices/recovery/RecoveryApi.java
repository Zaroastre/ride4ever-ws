/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.recovery;

interface RecoveryApi {
    Recovery findAccountByEmail(final String email) throws RuntimeException;
    Recovery sendCodeByEmailTo(final Recovery recovery) throws RuntimeException;
    Recovery sendCodeBySmsTo(final Recovery recovery) throws RuntimeException;
    Recovery verifyCode(final Recovery recovery) throws RuntimeException;
    Recovery changePassword(final Recovery recovery) throws RuntimeException;
}
