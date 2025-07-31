package team.retum.jobis.domain.review.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;
import team.retum.jobis.domain.review.model.InterviewLocation;
import team.retum.jobis.domain.review.model.InterviewType;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.global.entity.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_review")
@Entity
public class ReviewEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "review", orphanRemoval = true)
    private final List<QnAEntity> qnAs = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;

    @Enumerated(EnumType.STRING)
    @Column(name = "interview_type", nullable = false)
    private InterviewType interviewType;

    @Enumerated(EnumType.STRING)
    @Column(name = "interview_location", nullable = false)
    private InterviewLocation interviewLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_id", nullable = false)
    private CodeEntity code;

    @Column(name = "interviewer_count", nullable = false)
    private Integer interviewerCount;

    @Column(name = "interview_question", columnDefinition = "VARCHAR(1000)", nullable = false)
    private String interviewQuestion;

    @Builder
    public ReviewEntity(Long id, StudentEntity student, CompanyEntity company,
                        InterviewType interviewType, InterviewLocation interviewLocation,
                        CodeEntity code, Integer interviewerCount, String interviewQuestion) {
        this.id = id;
        this.student = student;
        this.company = company;
        this.interviewType = interviewType;
        this.interviewLocation = interviewLocation;
        this.code = code;
        this.interviewerCount = interviewerCount;
        this.interviewQuestion = interviewQuestion;
    }
}
