package team.retum.jobis.domain.interview.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.recruitment.model.ProgressType;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class InterviewVO {

    private final Long id;
    private final ProgressType interviewType;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String interviewTime;
    private final String companyName;
    private final String location;
}
