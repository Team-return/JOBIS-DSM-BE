package team.retum.jobis.domain.student.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
