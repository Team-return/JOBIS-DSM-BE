package com.example.jobisapplication.domain.recruitment.dto.response;

import com.example.jobisapplication.domain.code.model.Code;
import com.example.jobisapplication.domain.recruitment.spi.vo.RecruitAreaVO;
import lombok.Builder;
import lombok.Getter;

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
                                .tech(recruitArea.getTechCodes().stream()
                                        .map(Code::getKeyword)
                                        .toList()
                                )
                                .job(recruitArea.getJobCodes())
                                .build()
                ).toList();
    }
}
