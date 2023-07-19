package com.example.jobisapplication.domain.bookmark.spi;

import com.example.jobisapplication.domain.bookmark.model.Bookmark;

public interface CommandBookmarkPort {

    void saveBookmark(Bookmark bookmark);

    void deleteBookmark(Bookmark bookmark);
}
