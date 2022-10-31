package com.example.jobis.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JobisException extends RuntimeException{
    private final ErrorCode errorCode;
}
