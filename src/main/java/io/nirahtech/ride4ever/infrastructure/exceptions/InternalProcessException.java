/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author METIVIER Nicolas
 * @since 0.0.1
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalProcessException extends RuntimeException {

    /**
     * 
     * @param message
     */
    public InternalProcessException(String message) {
        super(message);
    }

}
