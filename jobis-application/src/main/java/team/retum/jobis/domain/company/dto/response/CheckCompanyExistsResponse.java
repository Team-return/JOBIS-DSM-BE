package team.retum.jobis.domain.company.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CheckCompanyExistsResponse {

    private String companyName;
    private boolean exists;
}
