package team.retum.jobis.domain.bookmark.persistence.mapper;

import com.example.jobisapplication.domain.bookmark.model.Bookmark;
import com.example.jobisapplication.domain.recruitment.exception.RecruitmentNotFoundException;
import com.example.jobisapplication.domain.student.exception.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.bookmark.persistence.entity.BookmarkEntity;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentJpaRepository;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.student.persistence.repository.StudentJpaRepository;

import java.util.Optional;

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
                .recruitmentId(entity.getRecruitmentEntity().getId())
                .studentId(entity.getStudentEntity().getId())
                .build();
    }

    public Optional<Bookmark> toOptionalDomain(Optional<BookmarkEntity> entity) {
        return entity.map(
                bookmarkEntity -> Bookmark.builder()
                        .studentId(bookmarkEntity.getStudentEntity().getId())
                        .recruitmentId(bookmarkEntity.getRecruitmentEntity().getId())
                        .build()
        );
    }
}
