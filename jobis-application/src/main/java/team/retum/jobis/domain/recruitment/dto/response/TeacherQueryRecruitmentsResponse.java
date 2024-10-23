package team.retum.jobis.domain.recruitment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.company.model.CompanyType;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;
import team.retum.jobis.domain.recruitment.spi.vo.TeacherRecruitmentVO;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class TeacherQueryRecruitmentsResponse {

    private final List<TeacherRecruitmentResponse> recruitments;

    @Getter
    @NoArgsConstructor(force = true)
    @AllArgsConstructor
    @Builder
    public static class TeacherRecruitmentResponse {

        private long id;

        private RecruitStatus status;

        private String companyName;

        private CompanyType companyType;

        private String hiringJobs;

        private long totalHiringCount;

        private long applicationRequestedCount;

        private long applicationApprovedCount;

        private LocalDate startDate;

        private LocalDate endDate;

        private long companyId;

        private Boolean isConvertible;

        public static TeacherRecruitmentResponse from(TeacherRecruitmentVO recruitment) {
            return TeacherRecruitmentResponse.builder()
                .id(recruitment.getRecruitmentId())
                .status(recruitment.getRecruitStatus())
                .companyName(recruitment.getCompanyName())
                .companyType(recruitment.getCompanyType())
                .startDate(recruitment.getStartDate())
                .endDate(recruitment.getEndDate())
                .applicationRequestedCount(recruitment.getRequestedApplicationCount())
                .applicationApprovedCount(recruitment.getApprovedApplicationCount())
                .totalHiringCount(recruitment.getTotalHiringCount())
                .hiringJobs(recruitment.getJobCodes())
                .companyId(recruitment.getCompanyId())
                .isConvertible(recruitment.getIsConvertible())
                .build();
        }
    }
}
