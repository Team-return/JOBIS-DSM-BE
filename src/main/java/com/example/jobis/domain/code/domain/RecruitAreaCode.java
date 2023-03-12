package com.example.jobis.domain.code.domain;

import com.example.jobis.domain.code.domain.enums.CodeType;
import com.example.jobis.domain.recruitment.domain.RecruitArea;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.validation.constraints.NotNull;


@Getter
@BatchSize(size = 200)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdClass(RecruitAreaCodeId.class)
public class RecruitAreaCode {
    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "BINARY(16)", name = "recruit_area_id", nullable = false)
    private RecruitArea recruitArea;

    @Id
    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    private String codeKeyword;

    @NotNull
    @Column(columnDefinition = "VARCHAR(4)")
    private CodeType codeType;
}