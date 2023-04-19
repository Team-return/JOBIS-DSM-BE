package team.returm.jobis.domain.acceptance.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class TeacherQueryFieldTraineesAndContractWorkersResponse {

    private List<TeacherQueryFieldTraineesResponse> fieldTraineesResponse;
    private List<TeacherQueryContractWorkersResponse> acceptancesResponse;


    @Getter
    @Builder
    public static class TeacherQueryFieldTraineesResponse {
        private String studentGcn;
        private String studentName;
        private LocalDate startDate;
        private LocalDate endDate;
    }

    @Getter
    @Builder
    public static class TeacherQueryContractWorkersResponse {
        private Long id;
        private String studentGcn;
        private String studentName;
        private LocalDate contractDate;
    }
}
