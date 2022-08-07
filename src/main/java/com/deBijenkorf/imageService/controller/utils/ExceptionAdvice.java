package com.deBijenkorf.imageService.controller.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestControllerAdvice
public class ExceptionAdvice {
    private static final Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(IllegalArgumentException .class)
    public Result handleIllegalArgumentException (Exception e){
        log.info("There is no such predefined type");
        return new Result(false, HttpStatus.NOT_FOUND.toString());
    }

    @ExceptionHandler(FileNotFoundException.class)
    public Result handleFileNotFoundException(Exception e){
        log.error("The source url is down");
        return new Result(false, HttpStatus.NOT_FOUND.toString());
    }

    @ExceptionHandler(IOException.class)
    public Result handleIOException(Exception e){
        log.warn("There is a problem writing resized image to S3 bucket");
        return new Result(false, HttpStatus.NOT_FOUND.toString());
    }
}
