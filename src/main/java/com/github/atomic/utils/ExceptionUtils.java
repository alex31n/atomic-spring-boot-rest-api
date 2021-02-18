package com.github.atomic.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ExceptionUtils {

    public static ResponseStatusException notFoundException() {
        return notFoundException("Not Found");
    }

    public static ResponseStatusException notFoundException(String message) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND,message);
    }
    public static ResponseStatusException notFoundException(String message, Throwable throwable) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND,message,throwable);
    }


    public static ResponseStatusException badRequestException() {
        return badRequestException("Bad Request");
    }
    public static ResponseStatusException badRequestException(String message) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST,message);
    }

    public static ResponseStatusException forbiddenException() {
        return forbiddenException("You don't have permission for this request");
    }
    public static ResponseStatusException forbiddenException(String message) {
        return new ResponseStatusException(HttpStatus.FORBIDDEN,message);
    }





}
