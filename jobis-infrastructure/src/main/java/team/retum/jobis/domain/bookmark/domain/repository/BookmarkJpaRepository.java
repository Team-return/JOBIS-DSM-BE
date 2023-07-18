package team.retum.jobis.domain.bookmark.domain.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.bookmark.domain.Bookmark;
import team.retum.jobis.domain.bookmark.domain.BookmarkId;
import team.retum.jobis.domain.recruitment.domain.Recruitment;
import team.retum.jobis.domain.student.domain.Student;

import java.util.Optional;

public interface BookmarkJpaRepository extends CrudRepository<Bookmark, BookmarkId> {

    Optional<Bookmark> findByRecruitmentAndStudent(Recruitment recruitment, Student student);

    boolean existsByRecruitmentAndStudent(Recruitment recruitment, Student student);
}
