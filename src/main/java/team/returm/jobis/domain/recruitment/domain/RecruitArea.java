package team.returm.jobis.domain.recruitment.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.returm.jobis.domain.code.domain.RecruitAreaCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RecruitArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "TINYINT(20)")
    private Integer hiredCount;

    @NotNull
    @Column(columnDefinition = "VARCHAR(200)")
    private String majorTask;

    @NotNull
    @Column(columnDefinition = "VARCHAR(40)")
    private String jobCodes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruitment_id", nullable = false)
    private Recruitment recruitment;

    @OneToMany(mappedBy = "recruitArea", orphanRemoval = true)
    private final List<RecruitAreaCode> codeList = new ArrayList<>();

    @Builder
    public RecruitArea(Integer hiredCount, String majorTask, String jobCodes, Recruitment recruitment) {
        this.hiredCount = hiredCount;
        this.majorTask = majorTask;
        this.jobCodes = jobCodes;
        this.recruitment = recruitment;
    }
}
