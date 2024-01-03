package team.retum.jobis.domain.company.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class StudentCompaniesVO {

    private final Long id;
    private final String name;
    private final String logoUrl;
    private final double take;
    private final boolean hasRecruitment;
}
