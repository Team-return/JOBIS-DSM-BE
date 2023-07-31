package team.retum.jobis.domain.bookmark.persistence.mapper;

import team.retum.jobis.domain.bookmark.model.Bookmark;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.student.exception.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
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
        RecruitmentEntity recruitment = recruitmentJpaRepository.findById(domain.getRecruitmentId())
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        StudentEntity student = studentJpaRepository.findById(domain.getStudentId())
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        return new BookmarkEntity(recruitment, student);
    }

    public Bookmark toDomain(BookmarkEntity entity) {
        return Bookmark.builder()
                .recruitmentId(entity.getRecruitment().getId())
                .studentId(entity.getStudent().getId())
                .build();
    }
}
