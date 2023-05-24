package team.returm.jobis.domain.company.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.returm.jobis.domain.company.domain.enums.CompanyType;

import java.util.List;

@Getter
@AllArgsConstructor
public class TeacherQueryCompaniesResponse {

    private final List<TeacherQueryCompanyResponse> companies;

    @Getter
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
