package team.retum.jobis.domain.notice.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.notice.model.ViewLog;
import team.retum.jobis.domain.notice.persistence.entity.NoticeEntity;
import team.retum.jobis.domain.notice.persistence.entity.ViewLogEntity;
import team.retum.jobis.domain.notice.persistence.repository.NoticeJpaRepository;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.student.persistence.repository.StudentJpaRepository;

@RequiredArgsConstructor
@Component
public class ViewLogMapper {

    private final NoticeJpaRepository noticeJpaRepository;
    private final StudentJpaRepository studentJpaRepository;

    public ViewLogEntity toEntity(ViewLog viewLog) {
        NoticeEntity notice = noticeJpaRepository.getReferenceById(viewLog.getNoticeId());
        StudentEntity student = studentJpaRepository.getReferenceById(viewLog.getStudentId());

        return ViewLogEntity.builder()
                .notice(notice)
                .student(student)
                .viewedAt(viewLog.getViewedAt())
                .build();
    }

    public ViewLog toDomain(ViewLogEntity entity) {
        return ViewLog.builder()
                .id(entity.getId())
                .noticeId(entity.getNotice().getId())
                .studentId(entity.getStudent().getId())
                .viewedAt(entity.getViewedAt())
                .build();
    }
}
