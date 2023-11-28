package team.retum.jobis.domain.recruitment.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.company.model.CompanyType;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class StudentRecruitmentVO {

    private final long recruitmentId;
    private final String companyName;
    private final int trainPay;
    private final boolean militarySupport;
    private final String companyLogoUrl;
    private final String jobCodes;
    private final boolean isBookmarked;

}
