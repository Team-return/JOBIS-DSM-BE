package com.example.jobis.domain.code;

import com.example.jobis.domain.code.domain.Code;
import com.example.jobis.domain.code.domain.RecruitAreaCodeId;
import com.example.jobis.domain.recruit.domain.RecruitArea;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdClass(RecruitAreaCodeId.class)
public class RecruitAreaCode {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private RecruitArea recruitAreaId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private Code codeId;
}
