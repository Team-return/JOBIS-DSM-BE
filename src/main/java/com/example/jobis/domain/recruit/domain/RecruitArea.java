package com.example.jobis.domain.recruit.domain;

import com.example.jobis.domain.code.domain.RecruitAreaCode;
import com.example.jobis.global.entity.BaseUUIDEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RecruitArea extends BaseUUIDEntity {

    private Integer hiredCount;

    private String majorTask;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruitment_id", nullable = false)
    private Recruitment recruitment;

    @OneToMany(mappedBy = "recruitAreaId", orphanRemoval = true)
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
