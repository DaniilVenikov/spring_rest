package ru.venikov.spring.boot_security.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.venikov.spring.boot_security.exceptions.UserNotCreatedException;
import ru.venikov.spring.boot_security.exceptions.UserNotFoundException;
import ru.venikov.spring.boot_security.exceptions.UserNotUpdatedException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> unfHandler(UserNotFoundException exception) {
        return new ResponseEntity<>("Exception with trying to get a user", HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotCreatedException.class)
    public ResponseEntity<String> uncHandler(UserNotCreatedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotUpdatedException.class)
    public ResponseEntity<String> unuHandler(UserNotUpdatedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
