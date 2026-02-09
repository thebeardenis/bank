package org.quick.bank.exceptions;

public class UserEmailAlreadyUse extends RuntimeException {
    public UserEmailAlreadyUse(String message) {
        super(message);
    }
}
