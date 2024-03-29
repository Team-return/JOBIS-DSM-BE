package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.dto.response.TotalPageCountResponse;
import team.retum.jobis.common.util.NumberUtil;
import team.retum.jobis.domain.application.dto.response.TeacherQueryApplicationsResponse;
import team.retum.jobis.domain.application.dto.ApplicationFilter;
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

        List<ApplicationVO> applicationVOs = queryApplicationPort.queryApplicationByConditions(applicationFilter);

        return TeacherQueryApplicationsResponse.of(applicationVOs);
    }

    public TotalPageCountResponse getTotalPageCount(ApplicationStatus applicationStatus, String studentName) {
        return new TotalPageCountResponse(
                NumberUtil.getTotalPageCount(
                        queryApplicationPort.queryApplicationCountByCondition(applicationStatus, studentName), 11
                )
        );
    }
}
