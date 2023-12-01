package com.example.ecom.advicee;

import com.example.ecom.common.EmptyInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllerAdvicee {
    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<String> handlerEmpty(EmptyInputException emptyInputException) {

//        String errorMessage = "Input cannot be empty: " + emptyInputException.getErrorMEssage();
//        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        return new   ResponseEntity<String> ("input field is empty ,please look into ",HttpStatus.BAD_REQUEST);

    }




}