package team.retum.jobis.domain.recruitment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;
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

        private Boolean militarySupport;

        private String hiringJobs;

        private boolean bookmarked;

        private RecruitStatus status;

        public static StudentRecruitmentResponse from(StudentRecruitmentVO recruitment) {
            return StudentRecruitmentResponse.builder()
                .id(recruitment.getRecruitmentId())
                .companyName(recruitment.getCompanyName())
                .trainPay(0)
                .hiringJobs(recruitment.getJobCodes())
                .militarySupport(recruitment.getMilitarySupport())
                .companyProfileUrl(recruitment.getCompanyLogoUrl())
                .bookmarked(recruitment.isBookmarked())
                .status(recruitment.getStatus())
                .build();
        }
    }
}
