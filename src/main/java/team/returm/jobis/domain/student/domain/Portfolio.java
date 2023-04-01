package team.returm.jobis.domain.student.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Portfolio {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_id")
    private Long id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(300)")
    private String portfolioUrl;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;


    @Builder
    public Portfolio(String portfolioUrl, Student student) {
        this.portfolioUrl = portfolioUrl;
        this.student = student;
    }
}
