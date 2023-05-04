package team.returm.jobis.domain.bookmark.domain.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BookmarkRepository {

    private final BookmarkJpaRepository bookmarkJpaRepository;
}
