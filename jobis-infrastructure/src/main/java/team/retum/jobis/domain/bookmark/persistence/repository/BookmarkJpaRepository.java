package team.retum.jobis.domain.bookmark.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.bookmark.persistence.BookmarkEntity;
import team.retum.jobis.domain.bookmark.persistence.BookmarkId;
import team.retum.jobis.domain.recruitment.persistence.RecruitmentEntity;
import team.retum.jobis.domain.student.persistence.StudentEntity;

import java.util.Optional;

public interface BookmarkJpaRepository extends CrudRepository<BookmarkEntity, BookmarkId> {

    Optional<BookmarkEntity> findByRecruitmentAndStudent(RecruitmentEntity recruitmentEntity, StudentEntity studentEntity);

    boolean existsByRecruitmentAndStudent(RecruitmentEntity recruitmentEntity, StudentEntity studentEntity);
}
