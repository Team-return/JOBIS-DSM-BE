package team.retum.jobis.domain.bookmark.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.bookmark.model.Bookmark;
import team.retum.jobis.domain.bookmark.persistence.entity.BookmarkEntity;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentJpaRepository;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.student.persistence.repository.StudentJpaRepository;

@RequiredArgsConstructor
@Component
public class BookmarkMapper {

    private final RecruitmentJpaRepository recruitmentJpaRepository;
    private final StudentJpaRepository studentJpaRepository;

    public BookmarkEntity toEntity(Bookmark domain) {
        RecruitmentEntity recruitment = recruitmentJpaRepository.getReferenceById(domain.getRecruitmentId());
        StudentEntity student = studentJpaRepository.getReferenceById(domain.getStudentId());

        return new BookmarkEntity(recruitment, student);
    }

    public Bookmark toDomain(BookmarkEntity entity) {
        return Bookmark.builder()
            .recruitmentId(entity.getRecruitment().getId())
            .studentId(entity.getStudent().getId())
            .build();
    }
}
