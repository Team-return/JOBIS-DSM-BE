package team.retum.jobis.domain.recruitment.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudentRecruitmentVO {

    private final long recruitmentId;
    private final String companyName;
    private final int trainPay;
    private final Boolean militarySupport;
    private final String companyLogoUrl;
    private final String jobCodes;
    private final boolean isBookmarked;

}
