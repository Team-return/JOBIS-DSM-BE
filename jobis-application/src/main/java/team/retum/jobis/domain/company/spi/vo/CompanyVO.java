package team.retum.jobis.domain.company.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompanyVO {

    private final Long id;
    private final String companyName;
    private final String logoUrl;
}
