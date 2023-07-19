package team.retum.jobis.domain.bookmark.persistence;

import com.example.jobisapplication.domain.bookmark.model.Bookmark;
import com.example.jobisapplication.domain.bookmark.spi.BookmarkPort;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.bookmark.persistence.mapper.BookmarkMapper;
import team.retum.jobis.domain.bookmark.persistence.repository.BookmarkJpaRepository;
import team.retum.jobis.domain.bookmark.persistence.repository.vo.QQueryStudentBookmarksVO;
import team.retum.jobis.domain.bookmark.persistence.repository.vo.QueryStudentBookmarksVO;

import java.util.List;
import java.util.Optional;

import static team.retum.jobis.domain.bookmark.persistence.QBookmark.bookmark;
import static team.retum.jobis.domain.company.persistence.QCompany.company;
import static team.retum.jobis.domain.recruitment.persistence.QRecruitment.recruitment;
import static team.retum.jobis.domain.student.persistence.QStudent.student;

@RequiredArgsConstructor
@Component
public class BookmarkPersistenceAdapter implements BookmarkPort {

    private final BookmarkJpaRepository bookmarkJpaRepository;
    private final BookmarkMapper bookmarkMapper;
    private final JPAQueryFactory queryFactory;

    @Override
    public void saveBookmark(Bookmark bookmark) {
        bookmarkJpaRepository.save(
                bookmarkMapper.toEntity(bookmark)
        );
    }

    @Override
    public Optional<Bookmark> queryBookmarkByRecruitmentIdAndStudentId(Long recruitmentId, Long studentId) {
        return bookmarkMapper.toOptionalDomain(
                bookmarkJpaRepository.findByRecruitmentEntityIdAndStudentEntityId(recruitmentId, studentId)
        )
    }

    @Override
    public void deleteBookmark(Bookmark bookmark) {
        bookmarkJpaRepository.delete(
                bookmarkMapper.toEntity(bookmark)
        );
    }

    @Override
    public boolean existsBookmarkByRecruitmentAndStudent(Long recruitmentId, Long studentId) {
        return bookmarkJpaRepository.existsByRecruitmentEntityIdAndStudentEntityId(recruitmentId, studentId);
    }

    @Override
    public List<QueryStudentBookmarksVO> queryBookmarksByStudentId(Long studentId) {
        return queryFactory
                .select(
                        new QQueryStudentBookmarksVO(
                                company.name,
                                recruitment.id,
                                bookmark.createdAt
                        )
                )
                .from(bookmark)
                .join(bookmark.recruitment, recruitment)
                .join(recruitment.company, company)
                .join(bookmark.student, student)
                .where(student.id.eq(studentId))
                .orderBy(bookmark.createdAt.desc())
                .fetch();
    }
}
