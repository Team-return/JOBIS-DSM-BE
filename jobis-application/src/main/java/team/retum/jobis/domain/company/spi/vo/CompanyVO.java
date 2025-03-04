package team.retum.jobis.domain.company.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force=true)
@AllArgsConstructor
public class CompanyVO {

    private final Long id;
    private final String companyName;
    private final String logoUrl;
}
