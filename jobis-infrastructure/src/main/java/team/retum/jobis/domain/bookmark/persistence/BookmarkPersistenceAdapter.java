package team.retum.jobis.domain.bookmark.persistence;

import com.example.jobisapplication.domain.bookmark.model.Bookmark;
import com.example.jobisapplication.domain.bookmark.spi.BookmarkPort;
import com.example.jobisapplication.domain.bookmark.spi.vo.StudentBookmarksVO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.bookmark.persistence.mapper.BookmarkMapper;
import team.retum.jobis.domain.bookmark.persistence.repository.BookmarkJpaRepository;
import team.retum.jobis.domain.bookmark.persistence.repository.vo.QQueryStudentBookmarksVO;
import team.retum.jobis.domain.bookmark.persistence.repository.vo.QueryStudentBookmarksVO;

import java.util.List;
import java.util.Optional;

import static team.retum.jobis.domain.bookmark.persistence.entity.QBookmarkEntity.bookmarkEntity;
import static team.retum.jobis.domain.company.persistence.entity.QCompanyEntity.companyEntity;
import static team.retum.jobis.domain.recruitment.persistence.entity.QRecruitmentEntity.recruitmentEntity;
import static team.retum.jobis.domain.student.persistence.entity.QStudentEntity.studentEntity;

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
        );
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
    public List<StudentBookmarksVO> queryBookmarksByStudentId(Long studentId) {
        return queryFactory
                .select(
                        new QQueryStudentBookmarksVO(
                                companyEntity.name,
                                recruitmentEntity.id,
                                bookmarkEntity.createdAt
                        )
                )
                .from(bookmarkEntity)
                .join(bookmarkEntity.recruitmentEntity, recruitmentEntity)
                .join(recruitmentEntity.companyEntity, companyEntity)
                .join(bookmarkEntity.studentEntity, studentEntity)
                .where(studentEntity.id.eq(studentId))
                .orderBy(bookmarkEntity.createdAt.desc())
                .fetch().stream()
                .map(bookmark -> (StudentBookmarksVO) bookmark)
                .toList();
    }
}
