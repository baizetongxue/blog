package com.sc.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundIndexException extends RuntimeException {
    public NotFoundIndexException() {
        super();
    }
    public NotFoundIndexException(String message) {
        super(message);
    }
    public NotFoundIndexException(String message, Throwable cause) {
        super(message, cause);
    }
}
