package team.returm.jobis.domain.bookmark.domain.repository;

import org.springframework.data.repository.CrudRepository;
import team.returm.jobis.domain.bookmark.domain.BookmarkId;
import team.returm.jobis.domain.bookmark.domain.Bookmark;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.student.domain.Student;

import java.util.Optional;

public interface BookmarkJpaRepository extends CrudRepository<Bookmark, BookmarkId> {

    Optional<Bookmark> findByRecruitmentAndStudent(Recruitment recruitment, Student student);
}
