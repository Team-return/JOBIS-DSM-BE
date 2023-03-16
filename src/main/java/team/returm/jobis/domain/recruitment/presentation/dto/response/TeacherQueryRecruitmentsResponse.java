package team.returm.jobis.domain.recruitment.presentation.dto.response;

import team.returm.jobis.domain.company.domain.enums.CompanyType;
import team.returm.jobis.domain.recruitment.domain.enums.RecruitStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class TeacherQueryRecruitmentsResponse {
    private final List<TeacherRecruitmentResponse> recruitments;

    @Getter
    @Builder
    public static class TeacherRecruitmentResponse {
        private UUID id;
        private RecruitStatus recruitmentStatus;
        private String companyName;
        private CompanyType companyType;
        private Set<String> recruitmentJob;
        private Integer recruitmentCount;
        private Integer applicationCount;
        private LocalDate start;
        private LocalDate end;
        private Boolean militarySupport;
    }
}
