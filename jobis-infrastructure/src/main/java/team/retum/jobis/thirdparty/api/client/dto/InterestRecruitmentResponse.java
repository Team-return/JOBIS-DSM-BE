package team.retum.jobis.thirdparty.api.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class InterestRecruitmentResponse {

    private List<String> recommendedCompanies;
}
