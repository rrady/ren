package com.ren.api.exceptions;

/**
 * Created by aneagu on 08/01/2020.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
