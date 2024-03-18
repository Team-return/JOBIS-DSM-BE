package team.retum.jobis.domain.recruitment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.code.model.CodeResponse;

import java.util.List;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class RecruitAreaResponse {

    private final Long id;

    private final List<CodeResponse> job;

    private final List<CodeResponse> tech;

    private final int hiring;

    private final String majorTask;

    private final String preferentialTreatment;
}
