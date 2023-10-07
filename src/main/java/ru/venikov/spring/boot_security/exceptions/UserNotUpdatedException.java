package ru.venikov.spring.boot_security.exceptions;

import javax.management.remote.JMXServerErrorException;

public class UserNotUpdatedException extends RuntimeException {
    public UserNotUpdatedException(String message) {
        super(message);
    }
}
