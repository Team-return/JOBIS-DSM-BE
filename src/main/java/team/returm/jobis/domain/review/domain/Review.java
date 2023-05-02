package team.returm.jobis.domain.review.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.returm.jobis.domain.company.domain.Company;
import team.returm.jobis.domain.review.domain.enums.ReviewType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(1000)")
    private String content;

    @NotNull
    @Column(columnDefinition = "VARCHAR(10)")
    private String studentName;

    @NotNull
    @Column(columnDefinition = "CHAR(4)")
    private String studentGcn;

    @NotNull
    @Column(columnDefinition = "YEAR")
    private Integer year;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(18)")
    private ReviewType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Builder
    public Review(String content, String studentName, String studentGcn,
                  Integer year, ReviewType reviewType, Company company) {
        this.content = content;
        this.studentName = studentName;
        this.studentGcn = studentGcn;
        this.year = year;
        this.type = reviewType;
        this.company = company;
    }
}
