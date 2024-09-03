package team.retum.jobis.domain.bookmark.spi;

import team.retum.jobis.domain.bookmark.model.Bookmark;

public interface CommandBookmarkPort {

    void save(Bookmark bookmark);

    void delete(Bookmark bookmark);
}
