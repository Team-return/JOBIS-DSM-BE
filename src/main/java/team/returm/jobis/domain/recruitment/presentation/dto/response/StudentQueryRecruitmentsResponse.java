package team.returm.jobis.domain.recruitment.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
public class StudentQueryRecruitmentsResponse {

    private final List<StudentRecruitmentResponse> recruitments;

    @Getter
    @Builder
    public static class StudentRecruitmentResponse {
        private Long recruitId;
        private String companyName;
        private String companyProfileUrl;
        private Integer trainPay;
        private boolean military;
        private Integer totalHiring;
        private Set<String> jobCodeList;
    }
}
