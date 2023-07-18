package com.example.jobisapplication.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JobisException extends RuntimeException {

    private final ErrorProperty errorProperty;
}
