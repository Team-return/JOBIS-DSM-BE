package team.retum.jobis.domain.bookmark.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.bookmark.persistence.Bookmark;
import team.retum.jobis.domain.bookmark.persistence.BookmarkId;
import team.retum.jobis.domain.recruitment.persistence.Recruitment;
import team.retum.jobis.domain.student.persistence.Student;

import java.util.Optional;

public interface BookmarkJpaRepository extends CrudRepository<Bookmark, BookmarkId> {

    Optional<Bookmark> findByRecruitmentAndStudent(Recruitment recruitment, Student student);

    boolean existsByRecruitmentAndStudent(Recruitment recruitment, Student student);
}
