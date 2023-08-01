package team.retum.jobis.domain.recruitment.persistence.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.code.persistence.entity.RecruitAreaCodeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_recruit_area")
@Entity
public class RecruitAreaEntity {

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
    private RecruitmentEntity recruitment;

    @OneToMany(mappedBy = "recruitArea", orphanRemoval = true)
    private List<RecruitAreaCodeEntity> recruitAreaCodes = new ArrayList<>();

    @Builder
    public RecruitAreaEntity(Long id, Integer hiredCount, String majorTask,
                             String jobCodes, RecruitmentEntity recruitmentEntity) {
        this.id = id;
        this.hiredCount = hiredCount;
        this.majorTask = majorTask;
        this.jobCodes = jobCodes;
        this.recruitment = recruitmentEntity;
    }
}
