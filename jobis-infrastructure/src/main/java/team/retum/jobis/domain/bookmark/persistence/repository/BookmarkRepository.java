package team.retum.jobis.domain.bookmark.persistence.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.bookmark.persistence.entity.BookmarkEntity;
import team.retum.jobis.domain.bookmark.persistence.repository.vo.QQueryStudentBookmarksVO;
import team.retum.jobis.domain.bookmark.persistence.repository.vo.QueryStudentBookmarksVO;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;

import java.util.List;
import java.util.Optional;

import static team.retum.jobis.domain.bookmark.persistence.QBookmark.bookmark;
import static team.retum.jobis.domain.company.persistence.QCompany.company;
import static team.retum.jobis.domain.recruitment.persistence.QRecruitment.recruitment;
import static team.retum.jobis.domain.student.persistence.QStudent.student;

@RequiredArgsConstructor
@Repository
public class BookmarkRepository {

    private final BookmarkJpaRepository bookmarkJpaRepository;
    private final JPAQueryFactory queryFactory;

    public void saveBookmark(BookmarkEntity bookmarkEntity) {
        bookmarkJpaRepository.save(bookmarkEntity);
    }

    public Optional<BookmarkEntity> queryBookmarkByRecruitmentAndStudent(RecruitmentEntity recruitmentEntity, StudentEntity studentEntity) {
        return bookmarkJpaRepository.findByRecruitmentAndStudent(recruitmentEntity, studentEntity);
    }

    public void deleteBookmark(BookmarkEntity bookmarkEntity) {
        bookmarkJpaRepository.delete(bookmarkEntity);
    }

    public boolean existsBookmarkByRecruitmentAndStudent(RecruitmentEntity recruitmentEntity, StudentEntity studentEntity) {
        return bookmarkJpaRepository.existsByRecruitmentAndStudent(recruitmentEntity, studentEntity);
    }

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