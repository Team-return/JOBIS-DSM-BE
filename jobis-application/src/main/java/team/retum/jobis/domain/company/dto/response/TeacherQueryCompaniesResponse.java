package team.retum.jobis.domain.company.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.company.model.CompanyType;

import java.util.List;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class TeacherQueryCompaniesResponse {

    private final List<TeacherQueryCompanyResponse> companies;

    @Getter
    @NoArgsConstructor(force = true)
    @AllArgsConstructor
    @Builder
    public static class TeacherQueryCompanyResponse {

        private final Long companyId;
        private final String companyName;
        private final String region;
        private final String businessArea;
        private final Integer workersCount;
        private final double take;
        private final CompanyType companyType;
        private final Boolean convention;
        private final Boolean personalContact;
        private final Integer recentRecruitYear;
        private final Long totalAcceptanceCount;
        private final Long reviewCount;
    }
}
