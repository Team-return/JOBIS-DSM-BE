package team.retum.jobis.domain.bug.persistence.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.bug.model.DevelopmentArea;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.global.entity.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_bug_report")
@Entity
public class BugReportEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(20)")
    private String title;

    @NotNull
    @Column(columnDefinition = "VARCHAR(400)")
    private String content;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(7)")
    private DevelopmentArea developmentArea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity student;

    @ElementCollection
    @CollectionTable(name = "tbl_bug_attachment", joinColumns = @JoinColumn(name = "bug_report_id"))
    private List<BugAttachmentEntity> attachments = new ArrayList<>();

    @Builder
    public BugReportEntity(Long id, String title, String content, List<BugAttachmentEntity> attachments,
                           DevelopmentArea developmentArea, StudentEntity student) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.developmentArea = developmentArea;
        this.attachments = attachments;
        this.student = student;
    }
}
