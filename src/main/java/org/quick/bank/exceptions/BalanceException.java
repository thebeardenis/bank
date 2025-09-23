package org.quick.bank.exceptions;

public class BalanceException extends RuntimeException {
    public BalanceException(String message) {
        super(message);
    }
}
