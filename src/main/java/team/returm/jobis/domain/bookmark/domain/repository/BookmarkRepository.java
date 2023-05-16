package team.returm.jobis.domain.bookmark.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.returm.jobis.domain.bookmark.domain.Bookmark;
import team.returm.jobis.domain.bookmark.domain.repository.vo.QQueryStudentBookmarksVO;
import team.returm.jobis.domain.bookmark.domain.repository.vo.QueryStudentBookmarksVO;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.student.domain.Student;

import java.util.List;
import java.util.Optional;

import static team.returm.jobis.domain.bookmark.domain.QBookmark.bookmark;
import static team.returm.jobis.domain.company.domain.QCompany.company;
import static team.returm.jobis.domain.recruitment.domain.QRecruitment.recruitment;
import static team.returm.jobis.domain.student.domain.QStudent.student;

@RequiredArgsConstructor
@Repository
public class BookmarkRepository {

    private final BookmarkJpaRepository bookmarkJpaRepository;
    private final JPAQueryFactory queryFactory;

    public void saveBookmark(Bookmark bookmark) {
        bookmarkJpaRepository.save(bookmark);
    }

    public Optional<Bookmark> queryBookmarkByRecruitmentAndStudent(Recruitment recruitment, Student student) {
        return bookmarkJpaRepository.findByRecruitmentAndStudent(recruitment, student);
    }

    public void deleteBookmark(Bookmark bookmark) {
        bookmarkJpaRepository.delete(bookmark);
    }

    public boolean existsBookmarkByRecruitmentAndStudent(Recruitment recruitment, Student student) {
        return bookmarkJpaRepository.existsByRecruitmentAndStudent(recruitment, student);
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
