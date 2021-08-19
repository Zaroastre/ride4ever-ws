package io.nirahtech.ride4ever.infrastructure.exceptions;

public class DuplicateConstraintException extends BadRequestException {

    public DuplicateConstraintException(String message) {
        super(message);
    }

}
