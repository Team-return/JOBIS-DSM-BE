package com.example.jobisapplication.domain.code.exception.error;

import com.example.jobisapplication.common.error.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import com.example.jobisapplication.common.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum CodeErrorCode implements ErrorProperty {

    RECRUIT_AREA_CODE_NOT_FOUND(HttpStatus.NOT_FOUND, "Recruit Area Code Not Found"),
    CODE_NOT_FOUND(HttpStatus.NOT_FOUND, "Code Not Found");

    private final HttpStatus status;
    private final String message;
}