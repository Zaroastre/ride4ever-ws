/******************************************************************
 * Copyright 2021 Ride4Ever
 *
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.engine.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class PasswordEncrypt extends BCryptPasswordEncoder{
    public PasswordEncrypt() { }

    /**
     *
     * @param password
     * @return
     */
    public static final String encrypt(final String password) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            messageDigest = null;
        }
        if (messageDigest != null) {
            messageDigest.update(password.getBytes());
            byte[] digest = messageDigest.digest();
            return DatatypeConverter.printHexBinary(digest).toUpperCase();
        }
        return null;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return encrypt(rawPassword.toString());
    }
}
