package team.retum.jobis.domain.recruitment.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Getter
@Builder
public class CreateRecruitAreaRequest {

    private List<Long> jobCodes;

    private List<Long> techCodes;

    private int hiring;

    private String majorTask;
}
