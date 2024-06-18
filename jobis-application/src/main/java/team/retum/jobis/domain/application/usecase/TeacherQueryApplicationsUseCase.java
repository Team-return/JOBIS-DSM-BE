package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.dto.response.TotalPageCountResponse;
import team.retum.jobis.common.util.NumberUtil;
import team.retum.jobis.domain.application.dto.ApplicationFilter;
import team.retum.jobis.domain.application.dto.response.ApplicationCountResponse;
import team.retum.jobis.domain.application.dto.response.TeacherQueryApplicationsResponse;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.application.spi.vo.ApplicationVO;

import java.time.Year;
import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class TeacherQueryApplicationsUseCase {

    private final QueryApplicationPort queryApplicationPort;

    public TeacherQueryApplicationsResponse execute(
        ApplicationStatus applicationStatus,
        String studentName,
        Long recruitmentId,
        Year year
    ) {
        ApplicationFilter applicationFilter = ApplicationFilter.builder()
            .recruitmentId(recruitmentId)
            .applicationStatus(applicationStatus)
            .studentName(studentName)
            .year(year)
            .build();

        List<ApplicationVO> applicationVOs = queryApplicationPort.getAllByConditions(applicationFilter);

        return TeacherQueryApplicationsResponse.of(applicationVOs);
    }

    public ApplicationCountResponse countApplications() {
        Long count = queryApplicationPort.countApplications();

        return new ApplicationCountResponse(count);
    }

    public TotalPageCountResponse getTotalPageCount(ApplicationStatus applicationStatus, String studentName) {
        return new TotalPageCountResponse(
            NumberUtil.getTotalPageCount(
                queryApplicationPort.getCountByCondition(applicationStatus, studentName), 11
            )
        );
    }
}
