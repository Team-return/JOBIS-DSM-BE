package team.retum.jobis.domain.company.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryReviewAvailableCompaniesResponse {

    private final List<CompanyResponse> companies;

    @Getter
    @AllArgsConstructor
    public static class CompanyResponse {
        private final Long id;
        private final String name;
    }
}
