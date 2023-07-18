package com.example.jobisapplication.domain.recruitment.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;
import team.retum.jobis.domain.recruitment.persistence.repository.vo.RecruitAreaVO;

import java.util.List;

@Getter
@Builder
public class RecruitAreaResponse {

    private final Long id;

    private final String job;

    private final List<String> tech;

    private final int hiring;

    private final String majorTask;

    public static List<RecruitAreaResponse> of(List<RecruitAreaVO> recruitAreas) {
        return recruitAreas.stream()
                .map(recruitArea ->
                        RecruitAreaResponse.builder()
                                .id(recruitArea.getId())
                                .majorTask(recruitArea.getMajorTask())
                                .hiring(recruitArea.getHiredCount())
                                .tech(recruitArea.getTechCodeEntities().stream()
                                        .map(CodeEntity::getKeyword)
                                        .toList()
                                )
                                .job(recruitArea.getJobCodes())
                                .build()
                ).toList();
    }
}
