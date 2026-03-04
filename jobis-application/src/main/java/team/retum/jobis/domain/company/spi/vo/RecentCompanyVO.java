package team.retum.jobis.domain.company.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class RecentCompanyVO {

    private final Long companyId;
    private final String companyName;
    private final String companyIntroduce;
    private final String companyProfileUrl;
}
