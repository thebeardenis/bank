package org.quick.bank.exceptions;

public class UserNotSavedException extends RuntimeException {
    public UserNotSavedException(String message) {
        super(message);
    }
}
