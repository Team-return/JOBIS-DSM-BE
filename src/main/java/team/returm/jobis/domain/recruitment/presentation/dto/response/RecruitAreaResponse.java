package team.returm.jobis.domain.recruitment.presentation.dto.response;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecruitAreaResponse {
    private final UUID recruitAreaId;
    private final List<String> job;
    private final List<String> tech;
    private final int hiring;
    private final String majorTask;
}
