package team.retum.jobis.domain.recruitment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class StudentQueryRecruitmentsResponse {

    private final List<StudentRecruitmentResponse> recruitments;

    @Getter
    @Builder
    public static class StudentRecruitmentResponse {
        private long recruitId;
        private String companyName;
        private String companyProfileUrl;
        private int trainPay;
        private boolean military;
        private String jobCodeList;
        private boolean isBookmarked;
    }
}
