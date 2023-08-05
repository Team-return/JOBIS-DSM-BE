package team.retum.jobis.domain.acceptance.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class TeacherQueryFieldTraineesAndContractWorkersResponse {

    private final List<TeacherQueryFieldTraineesResponse> fieldTraineesResponse;
    private final List<TeacherQueryContractWorkersResponse> acceptancesResponse;

    @Getter
    @Builder
    public static class TeacherQueryFieldTraineesResponse {
        private Long applicationId;
        private String studentGcn;
        private String studentName;
        private LocalDate startDate;
        private LocalDate endDate;
    }

    @Getter
    @Builder
    public static class TeacherQueryContractWorkersResponse {
        private Long acceptanceId;
        private String studentGcn;
        private String studentName;
        private LocalDate contractDate;
    }
}
