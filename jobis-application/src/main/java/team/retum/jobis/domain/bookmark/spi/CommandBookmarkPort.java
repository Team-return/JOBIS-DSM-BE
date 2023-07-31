package team.retum.jobis.domain.bookmark.spi;

import team.retum.jobis.domain.bookmark.model.Bookmark;

public interface CommandBookmarkPort {

    void saveBookmark(Bookmark bookmark);

    void deleteBookmark(Bookmark bookmark);
}
