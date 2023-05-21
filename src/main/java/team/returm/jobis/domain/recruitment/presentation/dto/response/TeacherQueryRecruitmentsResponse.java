package team.returm.jobis.domain.recruitment.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.returm.jobis.domain.company.domain.enums.CompanyType;
import team.returm.jobis.domain.recruitment.domain.enums.RecruitStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
public class TeacherQueryRecruitmentsResponse {

    private final List<TeacherRecruitmentResponse> recruitments;

    private final int totalPageCount;

    @Getter
    @Builder
    public static class TeacherRecruitmentResponse {
        private Long id;
        private RecruitStatus recruitmentStatus;
        private String companyName;
        private CompanyType companyType;
        private Set<String> recruitmentJob;
        private Integer recruitmentCount;
        private Long applicationRequestedCount;
        private Long applicationApprovedCount;
        private LocalDate start;
        private LocalDate end;
        private Boolean militarySupport;
    }
}
