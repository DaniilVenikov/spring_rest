package ru.venikov.spring.boot_security.exceptions;

public class UserNotCreatedException extends RuntimeException {
    public UserNotCreatedException(String message) {
        super(message);
    }
}
