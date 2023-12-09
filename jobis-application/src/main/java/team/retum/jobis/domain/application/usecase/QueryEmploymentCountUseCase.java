package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.application.dto.response.QueryEmploymentCountResponse;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.student.spi.QueryStudentPort;

import java.time.Year;
import java.util.List;

import static team.retum.jobis.domain.application.model.ApplicationStatus.APPROVED;
import static team.retum.jobis.domain.application.model.ApplicationStatus.FIELD_TRAIN;
import static team.retum.jobis.domain.application.model.ApplicationStatus.PASS;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryEmploymentCountUseCase {

    private final QueryStudentPort queryStudentPort;

    public QueryEmploymentCountResponse execute() {
        int totalStudentCount = queryStudentPort.queryStudentCountByGradeAndEntranceYear(3, Year.now().getValue() - 2);
        long approvedApplicationCount = queryStudentPort.queryStudentCountByApplicationStatus(List.of(APPROVED));
        long passedApplicationCount = queryStudentPort.queryStudentCountByApplicationStatus(List.of(PASS, FIELD_TRAIN));

        return QueryEmploymentCountResponse.builder()
                .totalStudentCount(totalStudentCount)
                .approvedCount(approvedApplicationCount)
                .passedCount(passedApplicationCount)
                .build();
    }
}
