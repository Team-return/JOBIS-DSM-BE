package team.retum.jobis.domain.recruitment.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ManualRecruitmentVO {

    private final Long id;
    private final String companyName;
    private final String companyLogoUrl;
}
