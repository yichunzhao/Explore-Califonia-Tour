package com.ynz.ec.explorecali.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus for the successful operation used on the method level; for the error maybe used on the class level.
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TourNotFound extends RuntimeException {
    public TourNotFound(String message) {
        super(message);
    }
}
