package team.retum.jobis.domain.company.dto.response;

import lombok.Getter;

@Getter
public class CheckCompanyExistsResponse {

    private final String companyName;
    private final boolean exists;

    public CheckCompanyExistsResponse(String companyName, boolean exists) {
        this.companyName = companyName;
        this.exists = exists;
    }
}
