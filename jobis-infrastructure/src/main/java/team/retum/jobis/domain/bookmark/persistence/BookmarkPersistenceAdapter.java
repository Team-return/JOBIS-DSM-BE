package team.retum.jobis.domain.bookmark.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.bookmark.model.Bookmark;
import team.retum.jobis.domain.bookmark.persistence.mapper.BookmarkMapper;
import team.retum.jobis.domain.bookmark.persistence.repository.BookmarkJpaRepository;
import team.retum.jobis.domain.bookmark.persistence.repository.vo.QQueryBookmarkUserVO;
import team.retum.jobis.domain.bookmark.persistence.repository.vo.QQueryStudentBookmarksVO;
import team.retum.jobis.domain.bookmark.spi.BookmarkPort;
import team.retum.jobis.domain.bookmark.spi.vo.BookmarkUserVO;
import team.retum.jobis.domain.bookmark.spi.vo.StudentBookmarksVO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static team.retum.jobis.domain.bookmark.persistence.entity.QBookmarkEntity.bookmarkEntity;
import static team.retum.jobis.domain.company.persistence.entity.QCompanyEntity.companyEntity;
import static team.retum.jobis.domain.recruitment.persistence.entity.QRecruitmentEntity.recruitmentEntity;
import static team.retum.jobis.domain.student.persistence.entity.QStudentEntity.studentEntity;
import static team.retum.jobis.domain.user.persistence.entity.QUserEntity.userEntity;

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
    public Optional<Bookmark> getBookmarkByRecruitmentIdAndStudentId(Long recruitmentId, Long studentId) {
        return bookmarkJpaRepository.findByRecruitmentIdAndStudentId(recruitmentId, studentId)
            .map(bookmarkMapper::toDomain);
    }

    @Override
    public void deleteBookmark(Bookmark bookmark) {
        bookmarkJpaRepository.delete(
            bookmarkMapper.toEntity(bookmark)
        );
    }

    @Override
    public boolean existsBookmarkByRecruitmentAndStudent(Long recruitmentId, Long studentId) {
        return bookmarkJpaRepository.existsByRecruitmentIdAndStudentId(recruitmentId, studentId);
    }

    @Override
    public List<StudentBookmarksVO> getBookmarksByStudentId(Long studentId) {
        return queryFactory
            .select(
                new QQueryStudentBookmarksVO(
                    companyEntity.name,
                    companyEntity.companyLogoUrl,
                    recruitmentEntity.id,
                    bookmarkEntity.createdAt
                )
            )
            .from(bookmarkEntity)
            .join(bookmarkEntity.recruitment, recruitmentEntity)
            .join(recruitmentEntity.company, companyEntity)
            .join(bookmarkEntity.student, studentEntity)
            .where(studentEntity.id.eq(studentId))
            .orderBy(bookmarkEntity.createdAt.desc())
            .fetch().stream()
            .map(StudentBookmarksVO.class::cast)
            .toList();
    }

    @Override
    public Map<Long, List<BookmarkUserVO>> getBookmarkUserByRecruitmentIds(List<Long> recruitmentIds) {
        return queryFactory
            .select(
                new QQueryBookmarkUserVO(
                    recruitmentEntity.id,
                    userEntity.id,
                    companyEntity.name,
                    userEntity.token
                )
            )
            .from(bookmarkEntity)
            .join(bookmarkEntity.recruitment, recruitmentEntity)
            .join(recruitmentEntity.company, companyEntity)
            .join(bookmarkEntity.student, studentEntity)
            .join(studentEntity.userEntity, userEntity)
            .where(recruitmentEntity.id.in(recruitmentIds))
            .fetch().stream()
            .map(BookmarkUserVO.class::cast)
            .toList().stream()
            .collect(Collectors.groupingBy(BookmarkUserVO::getRecruitmentId));
    }
}
