package team.retum.jobis.domain.recruitment.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CreateRecruitAreaRequest {

    private List<Long> jobCodes;

    private List<Long> techCodes;

    private int hiring;

    private String majorTask;
}
