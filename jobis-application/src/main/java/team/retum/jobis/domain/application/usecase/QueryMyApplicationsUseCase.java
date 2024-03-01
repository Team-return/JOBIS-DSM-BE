package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.application.dto.response.QueryMyApplicationsResponse;
import team.retum.jobis.domain.application.dto.ApplicationFilter;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.application.spi.vo.ApplicationVO;
import team.retum.jobis.domain.student.model.Student;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryMyApplicationsUseCase {

    private final QueryApplicationPort queryApplicationPort;
    private final SecurityPort securityPort;

    public QueryMyApplicationsResponse execute() {
        Student student = securityPort.getCurrentStudent();

        ApplicationFilter applicationFilter = ApplicationFilter.builder()
                .studentId(student.getId())
                .build();

        List<ApplicationVO> applicationVOs = queryApplicationPort.queryApplicationByConditions(applicationFilter);

        return QueryMyApplicationsResponse.of(applicationVOs);
    }
}
