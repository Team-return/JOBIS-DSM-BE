package team.returm.jobis.domain.bookmark.domain.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BookmarkRepository {

    private final BookmarkJpaRepository bookmarkJpaRepository;

    public void saveBookmark(Bookmark bookmark) {
        bookmarkJpaRepository.save(bookmark);
    }

    public Optional<Bookmark> queryBookmarkByRecruitmentAndStudent(Recruitment recruitment, Student student) {
        return bookmarkJpaRepository.findByRecruitmentAndStudent(recruitment, student);
    }
}
