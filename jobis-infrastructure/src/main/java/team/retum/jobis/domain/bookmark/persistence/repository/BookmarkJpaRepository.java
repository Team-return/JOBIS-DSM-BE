package team.retum.jobis.domain.bookmark.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.bookmark.persistence.entity.BookmarkEntity;
import team.retum.jobis.domain.bookmark.persistence.entity.BookmarkId;

import java.util.Optional;

public interface BookmarkJpaRepository extends CrudRepository<BookmarkEntity, BookmarkId> {

    Optional<BookmarkEntity> findByRecruitmentIdAndStudentId(Long recruitmentId, Long studentId);

    boolean existsByRecruitmentIdAndStudentId(Long recruitmentId, Long studentId);
}
