package team.returm.jobis.domain.recruitment.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.returm.jobis.domain.code.domain.Code;
import team.returm.jobis.domain.recruitment.domain.repository.vo.RecruitAreaVO;

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
