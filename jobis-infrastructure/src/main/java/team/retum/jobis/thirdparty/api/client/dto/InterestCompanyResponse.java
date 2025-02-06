package team.retum.jobis.thirdparty.api.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class InterestCompanyResponse {

    private List<String> recommendedCompanies;
}
