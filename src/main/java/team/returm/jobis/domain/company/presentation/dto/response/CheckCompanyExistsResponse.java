package team.returm.jobis.domain.company.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CheckCompanyExistsResponse {

    private String companyName;
    private boolean exists;
}
