package team.retum.jobis.domain.recruitment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.company.model.CompanyType;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class TeacherQueryRecruitmentsResponse {

    private final List<TeacherRecruitmentResponse> recruitments;

    @Getter
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
    }
}
