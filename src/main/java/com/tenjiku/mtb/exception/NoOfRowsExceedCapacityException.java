package com.tenjiku.mtb.exception;

public class NoOfRowsExceedCapacityException extends RuntimeException {
    public NoOfRowsExceedCapacityException(String message) {
        super(message);
    }
}
