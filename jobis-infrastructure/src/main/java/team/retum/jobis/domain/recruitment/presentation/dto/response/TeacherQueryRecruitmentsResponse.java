package team.retum.jobis.domain.recruitment.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import com.example.jobisapplication.domain.company.domain.CompanyType;
import team.retum.jobis.domain.recruitment.persistence.enums.RecruitStatus;

import java.time.LocalDate;
import java.util.List;

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
        private String recruitmentJob;
        private Integer recruitmentCount;
        private Long applicationRequestedCount;
        private Long applicationApprovedCount;
        private LocalDate start;
        private LocalDate end;
        private Boolean militarySupport;
    }
}
