package team.retum.jobis.domain.student.persistence.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_portfolio")
@Entity
public class PortfolioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_id")
    private Long id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(300)")
    private String portfolioUrl;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity student;


    @Builder
    public PortfolioEntity(Long id, String portfolioUrl, StudentEntity studentEntity) {
        this.id = id;
        this.portfolioUrl = portfolioUrl;
        this.student = studentEntity;
    }
}
