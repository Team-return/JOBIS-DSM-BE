package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import static team.retum.jobis.domain.application.model.ApplicationStatus.FIELD_TRAIN;
import static team.retum.jobis.domain.application.model.ApplicationStatus.PASS;
import team.retum.jobis.domain.company.dto.response.EmploymentRatesResponse;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;
import team.retum.jobis.domain.company.spi.vo.CompanyVO;
import team.retum.jobis.domain.student.spi.QueryStudentPort;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryEmploymentRateUseCase {

    private final QueryCompanyPort queryCompanyPort;
    private final QueryStudentPort queryStudentPort;
    private static final Integer FIRTH_CLASS = 1;
    private static final Integer FOURTH_CLASS = 4;

    public EmploymentRatesResponse execute() {
        List<EmploymentRatesResponse.ClassResponse> classResponses = new ArrayList<>();

        for (Integer classNum = FIRTH_CLASS; classNum <= FOURTH_CLASS; classNum++) {
            List<CompanyVO> companies = queryCompanyPort.getEmploymentRateByClassNumber(classNum);
            long totalStudents = queryStudentPort.getTotalStudentsByClassNumber(classNum);
            long passedStudents = queryStudentPort.getPassedStudentsByClassNumber(classNum);

            classResponses.add(new EmploymentRatesResponse.ClassResponse(classNum, companies, totalStudents, passedStudents));
        }

        return new EmploymentRatesResponse(classResponses);
    }
}
