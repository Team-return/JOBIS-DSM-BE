package team.retum.jobis.domain.recruitment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RecruitAreaResponse {

    private final Long id;

    private final String job;

    private final List<String> tech;

    private final int hiring;

    private final String majorTask;
}
