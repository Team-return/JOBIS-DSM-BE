package team.retum.jobis.domain.interview.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.recruitment.model.ProgressType;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_interview")
public class InterviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private ProgressType interviewType;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate startDate;

    @Column(columnDefinition = "DATE")
    private LocalDate endDate;

    @Column(columnDefinition = "VARCHAR(8)", nullable = false)
    private String interviewTime;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String companyName;

    @Column(columnDefinition = "VARCHAR(80)", nullable = false)
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_number_id")
    private DocumentNumberEntity documentNumber;

    @Builder
    public InterviewEntity(Long id, ProgressType interviewType, LocalDate startDate, LocalDate endDate,
                           String interviewTime, String companyName, String location,
                           StudentEntity student, DocumentNumberEntity documentNumber) {
        this.id = id;
        this.interviewType = interviewType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.interviewTime = interviewTime;
        this.companyName = companyName;
        this.location = location;
        this.student = student;
        this.documentNumber = documentNumber;
    }
}
