package io.nirahtech.ride4ever.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author METIVIER Nicolas
 * @since 0.0.1
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalProcessException extends RuntimeException {

    public InternalProcessException(String message) {
        super(message);
    }

}