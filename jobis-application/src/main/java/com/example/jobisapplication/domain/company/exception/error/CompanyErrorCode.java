package com.example.jobisapplication.domain.company.exception.error;

import com.example.jobisapplication.common.error.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import com.example.jobisapplication.common.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum CompanyErrorCode implements ErrorProperty {

    COMPANY_NOT_FOUND(HttpStatus.NOT_FOUND, "Company Not Found"),

    COMPANY_ALREADY_EXISTS(HttpStatus.CONFLICT, "Company Already Exists"),

    COMPANY_NOT_EXISTS(HttpStatus.NOT_FOUND, "Company Not Exists");

    private final HttpStatus status;
    private final String message;
}