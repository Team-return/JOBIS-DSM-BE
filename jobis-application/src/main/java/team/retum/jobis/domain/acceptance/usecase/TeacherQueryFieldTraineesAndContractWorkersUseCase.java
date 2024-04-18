package team.retum.jobis.domain.acceptance.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.acceptance.dto.response.TeacherQueryFieldTraineesAndContractWorkersResponse;
import team.retum.jobis.domain.acceptance.dto.response.TeacherQueryFieldTraineesAndContractWorkersResponse.TeacherQueryContractWorkersResponse;
import team.retum.jobis.domain.acceptance.dto.response.TeacherQueryFieldTraineesAndContractWorkersResponse.TeacherQueryFieldTraineesResponse;
import team.retum.jobis.domain.acceptance.spi.QueryAcceptancePort;
import team.retum.jobis.domain.acceptance.spi.vo.AcceptanceVO;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.application.spi.vo.FieldTraineesVO;

import java.time.Year;
import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class TeacherQueryFieldTraineesAndContractWorkersUseCase {

    private final QueryApplicationPort queryApplicationPort;
    private final QueryAcceptancePort queryAcceptancePort;

    public TeacherQueryFieldTraineesAndContractWorkersResponse execute(Long companyId) {
        List<FieldTraineesVO> fieldTrainees = queryApplicationPort.getApplicationsFieldTraineesByCompanyId(companyId);
        List<AcceptanceVO> acceptances = queryAcceptancePort.getByCompanyIdAndYear(companyId, Year.now().getValue());

        return new TeacherQueryFieldTraineesAndContractWorkersResponse(
            TeacherQueryFieldTraineesResponse.from(fieldTrainees),
            TeacherQueryContractWorkersResponse.from(acceptances)
        );
    }
}
