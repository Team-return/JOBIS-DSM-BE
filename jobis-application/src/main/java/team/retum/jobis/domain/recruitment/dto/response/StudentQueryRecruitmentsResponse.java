package team.retum.jobis.domain.recruitment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.recruitment.spi.vo.StudentRecruitmentVO;

import java.util.List;

@Getter
@AllArgsConstructor
public class StudentQueryRecruitmentsResponse {

    private final List<StudentRecruitmentResponse> recruitments;

    @Getter
    @Builder
    public static class StudentRecruitmentResponse {

        private long id;

        private String companyName;

        private String companyProfileUrl;

        private int trainPay;

        private boolean militarySupport;

        private String hiringJobs;

        private boolean bookmarked;

        public static StudentRecruitmentResponse from(StudentRecruitmentVO recruitment) {
            return StudentRecruitmentResponse.builder()
                .id(recruitment.getRecruitmentId())
                .companyName(recruitment.getCompanyName())
                .trainPay(0)
                .hiringJobs(recruitment.getJobCodes())
                .militarySupport(recruitment.isMilitarySupport())
                .companyProfileUrl(recruitment.getCompanyLogoUrl())
                .bookmarked(recruitment.isBookmarked())
                .build();
        }
    }
}
