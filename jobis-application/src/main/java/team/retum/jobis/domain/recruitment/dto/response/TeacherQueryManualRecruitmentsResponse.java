package team.retum.jobis.domain.recruitment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.recruitment.spi.vo.ManualRecruitmentVO;

import java.util.List;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class TeacherQueryManualRecruitmentsResponse {

    private final List<TeacherManualRecruitmentResponse> recruitments;

    @Getter
    @Builder
    public static final class TeacherManualRecruitmentResponse {

        private final Long id;

        private final String companyName;

        private final String companyLogoUrl;

        public static TeacherManualRecruitmentResponse from(ManualRecruitmentVO recruitment) {
            return TeacherManualRecruitmentResponse.builder()
                .id(recruitment.getId())
                .companyName(recruitment.getCompanyName())
                .companyLogoUrl(recruitment.getCompanyLogoUrl())
                .build();
        }
    }
}
