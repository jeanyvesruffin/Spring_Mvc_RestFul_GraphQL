package com.ruffin.Spring_Mvc_Restful_GraphQl.exception;

public class ApplicationNotFoundException extends RuntimeException {

    public ApplicationNotFoundException(String exception) {
        super(exception);
    }
}
