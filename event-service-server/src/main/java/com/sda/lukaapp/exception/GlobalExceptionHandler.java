package com.sda.lukaapp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResource handleException(Exception ex) {
        ErrorResource errorResource = new ErrorResource();
        errorResource.setMessage(ex.getMessage());
        return errorResource;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public ErrorResource notFoundExceptionHandler(NotFoundException ex) {
        ErrorResource errorResource = new ErrorResource();
        errorResource.setMessage(ex.getMessage());
        return errorResource;
    }

}
