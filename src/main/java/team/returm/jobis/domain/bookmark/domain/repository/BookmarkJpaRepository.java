package team.returm.jobis.domain.bookmark.domain.repository;

import org.springframework.data.repository.CrudRepository;
import team.returm.jobis.domain.bookmark.domain.BookmarkId;
import team.returm.jobis.domain.bookmark.domain.Bookmark;

public interface BookmarkJpaRepository extends CrudRepository<Bookmark, BookmarkId> {
}
