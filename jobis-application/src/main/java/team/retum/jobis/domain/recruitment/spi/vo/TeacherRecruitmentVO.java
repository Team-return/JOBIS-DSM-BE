package team.retum.jobis.domain.recruitment.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.company.model.CompanyType;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class TeacherRecruitmentVO {

    private final long recruitmentId;
    private final RecruitStatus recruitStatus;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String companyName;
    private final CompanyType companyType;
    private final String jobCodes;
    private final long totalHiringCount;
    private final long requestedApplicationCount;
    private final long approvedApplicationCount;
    private final long companyId;
    private final Boolean hireConvertible;

}
