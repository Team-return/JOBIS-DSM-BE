package team.retum.jobis.domain.recruitment.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.code.persistence.entity.RecruitAreaCodeEntity;

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
    @Column(columnDefinition = "TINYINT")
    private Integer hiredCount;

    @NotNull
    @Column(columnDefinition = "VARCHAR(3500)")
    private String majorTask;

    @Column(columnDefinition = "VARCHAR(500)")
    private String preferentialTreatment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruitment_id", nullable = false)
    private RecruitmentEntity recruitment;

    @OneToMany(mappedBy = "recruitArea", cascade = CascadeType.ALL)
    private List<RecruitAreaCodeEntity> recruitAreaCodes = new ArrayList<>();

    @Builder
    public RecruitAreaEntity(Long id, Integer hiredCount, String majorTask, String preferentialTreatment,
                             RecruitmentEntity recruitmentEntity) {
        this.id = id;
        this.hiredCount = hiredCount;
        this.majorTask = majorTask;
        this.preferentialTreatment = preferentialTreatment;
        this.recruitment = recruitmentEntity;
    }

    public void addRecruitAreaCode(RecruitAreaCodeEntity recruitAreaCodeEntity) {
        recruitAreaCodes.add(recruitAreaCodeEntity);
    }

}
