package com.example.jobisapplication.domain.bookmark.exception;

import com.example.jobisapplication.domain.bookmark.exception.error.BookmarkErrorCode;
import com.example.jobisapplication.common.error.JobisException;

public class BookmarkAlreadyExistsException extends JobisException {

    public static final JobisException EXCEPTION = new BookmarkAlreadyExistsException();

    private BookmarkAlreadyExistsException() {
        super(BookmarkErrorCode.BOOKMARK_ALREADY_EXISTS);
    }
}