package com.ren.api.exceptions;

public class RenException extends Exception {
    private static final long serialVersionUID = -6542420026654100793L;

    private final String code;

    public RenException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}