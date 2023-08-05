package team.retum.jobis.domain.recruitment.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.code.model.Code;

import java.util.List;

@Getter
@AllArgsConstructor
public class RecruitAreaVO {

    private final Long id;

    private final Integer hiredCount;

    private final String majorTask;

    private final String jobCodes;

    private final List<Code> techCodes;
}
