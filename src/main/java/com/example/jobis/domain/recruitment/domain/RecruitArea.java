package com.example.jobis.domain.recruitment.domain;

import com.example.jobis.domain.code.domain.RecruitAreaCode;
import com.example.jobis.global.entity.BaseUUIDEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RecruitArea extends BaseUUIDEntity {

    @NotNull
    @Column(columnDefinition = "TINYINT(20)")
    private Integer hiredCount;

    @NotNull
    @Column(columnDefinition = "VARCHAR(200)")
    private String majorTask;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruitment_id", nullable = false)
    private Recruitment recruitment;

    @OneToMany(mappedBy = "recruitArea", orphanRemoval = true)
    private List<RecruitAreaCode> codeList = new ArrayList<>();

    @Builder
    public RecruitArea(Integer hiredCount, String majorTask, Recruitment recruitment) {
        this.hiredCount = hiredCount;
        this.majorTask = majorTask;
        this.recruitment = recruitment;
    }

    public void update(Integer hiredCount, String majorTask) {
        this.hiredCount = hiredCount;
        this.majorTask = majorTask;
    }
}
