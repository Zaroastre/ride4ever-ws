/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.infrastructure.exceptions;

public class DuplicateConstraintException extends BadRequestException {

    /**
     * 
     * @param message
     */
    public DuplicateConstraintException(String message) {
        super(message);
    }

}
