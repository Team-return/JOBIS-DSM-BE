package team.retum.jobis.domain.company.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.company.spi.vo.CompanyVO;

import java.util.List;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class EmploymentRatesResponse {

    private final List<ClassResponse> classes;

    @Getter
    @NoArgsConstructor(force = true)
    @AllArgsConstructor
    public static class ClassResponse {
        private final Integer classId;
        private final List<CompanyVO> employmentRateResponseList;
        private final long totalStudents;
        private final long passedStudents;
    }
}
