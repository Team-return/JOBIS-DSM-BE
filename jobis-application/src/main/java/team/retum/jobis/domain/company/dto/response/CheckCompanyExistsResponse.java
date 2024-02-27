package team.retum.jobis.domain.company.dto.response;

import lombok.Getter;

@Getter
public class CheckCompanyExistsResponse {

    private final String companyName;
    private final Long companyId;

    public CheckCompanyExistsResponse(String companyName, Long companyId) {
        this.companyName = companyName;
        this.companyId = companyId;
    }

    public CheckCompanyExistsResponse(String companyName) {
        this.companyName = companyName;
        this.companyId = null;
    }
}
