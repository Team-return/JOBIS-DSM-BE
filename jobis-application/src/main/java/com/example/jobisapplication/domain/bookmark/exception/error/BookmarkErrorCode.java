package com.example.jobisapplication.domain.bookmark.exception.error;

import com.example.jobisapplication.common.error.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import com.example.jobisapplication.common.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum BookmarkErrorCode implements ErrorProperty {

    BOOKMARK_NOT_FOUND(HttpStatus.NOT_FOUND, "BookmarkEntity Not Found"),

    BOOKMARK_ALREADY_EXISTS(HttpStatus.CONFLICT, "BookmarkEntity Already Exists");

    private final HttpStatus status;
    private final String message;
}
