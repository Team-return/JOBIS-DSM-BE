package team.retum.jobis.domain.notice.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import team.retum.jobis.domain.notice.model.Notice;
import team.retum.jobis.domain.notice.persistence.entity.NoticeEntity;
import team.retum.jobis.domain.student.model.Student;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "post_view_log")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ViewLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id", nullable = false)
    private NoticeEntity notice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity student;

    private LocalDateTime viewedAt;
}
