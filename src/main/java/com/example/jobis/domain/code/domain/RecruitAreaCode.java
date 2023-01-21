package com.example.jobis.domain.code.domain;

import com.example.jobis.domain.recruit.domain.RecruitArea;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdClass(RecruitAreaCodeId.class)
public class RecruitAreaCode {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_area_id", nullable = false)
    private RecruitArea recruitAreaId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_id", nullable = false)
    private Code codeId;
}
