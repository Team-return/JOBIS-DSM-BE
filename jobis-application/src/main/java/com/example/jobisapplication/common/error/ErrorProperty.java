package com.example.jobisapplication.common.error;

public interface ErrorProperty {

    HttpStatus getStatus();

    String getMessage();
}
