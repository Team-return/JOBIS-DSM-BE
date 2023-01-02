package com.example.jobis.domain.recruit.controller.dto.response;

import com.example.jobis.domain.code.domain.Code;
import com.example.jobis.domain.code.domain.RecruitAreaCode;
import com.example.jobis.domain.code.domain.enums.CodeType;
import com.example.jobis.domain.recruit.domain.RecruitArea;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class RecruitAreaResponse {
    private List<Long> job;
    private List<Long> tech;
    private int hiring;
    private String majorTask;

    public static RecruitAreaResponse of(RecruitArea recruitArea) {

        List<Long> jobCodes = recruitArea.getCodeList().stream()
                .map(RecruitAreaCode::getCodeId)
                .toList().stream()
                .filter(code -> code.getCodeType().equals(CodeType.JOB))
                .map(Code::getCode)
                .collect(Collectors.toList());

        List<Long> techCodes = recruitArea.getCodeList().stream()
                .map(RecruitAreaCode::getCodeId)
                .toList().stream()
                .filter(code -> code.getCodeType().equals(CodeType.TECH))
                .map(Code::getCode)
                .collect(Collectors.toList());

        return RecruitAreaResponse.builder()
                .job(jobCodes)
                .tech(techCodes)
                .hiring(recruitArea.getHiredCount())
                .majorTask(recruitArea.getMajorTask())
                .build();
    }
}
