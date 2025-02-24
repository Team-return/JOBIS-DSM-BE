package team.retum.jobis.domain.recruitment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.company.spi.vo.CompanyVO;

import java.util.List;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class TeacherQueryManualRecruitmentsResponse {

    private final List<TeacherManualRecruitmentResponse> recruitments;

    @Getter
    @NoArgsConstructor(force = true)
    @AllArgsConstructor
    @Builder
    public static final class TeacherManualRecruitmentResponse {

        private final Long id;

        private final String companyName;

        private final String companyLogoUrl;

        public static TeacherManualRecruitmentResponse from(CompanyVO recruitment) {
            return TeacherManualRecruitmentResponse.builder()
                .id(recruitment.getId())
                .companyName(recruitment.getCompanyName())
                .companyLogoUrl(recruitment.getLogoUrl())
                .build();
        }
    }
}
