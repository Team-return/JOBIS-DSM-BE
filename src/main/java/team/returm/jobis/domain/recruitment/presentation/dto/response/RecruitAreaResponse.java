package team.returm.jobis.domain.recruitment.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RecruitAreaResponse {
    private final Long recruitAreaId;
    private final List<String> job;
    private final List<String> tech;
    private final int hiring;
    private final String majorTask;
}
