package team.retum.jobis.domain.acceptance.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.acceptance.dto.response.TeacherQueryFieldTraineesAndContractWorkersResponse;
import team.retum.jobis.domain.acceptance.dto.response.TeacherQueryFieldTraineesAndContractWorkersResponse.TeacherQueryContractWorkersResponse;
import team.retum.jobis.domain.acceptance.dto.response.TeacherQueryFieldTraineesAndContractWorkersResponse.TeacherQueryFieldTraineesResponse;
import team.retum.jobis.domain.acceptance.model.Acceptance;
import team.retum.jobis.domain.acceptance.spi.QueryAcceptancePort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.application.spi.vo.FieldTraineesVO;
import team.retum.jobis.domain.student.model.Student;

import java.time.Year;
import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class TeacherQueryFieldTraineesAndContractWorkersUseCase {

    private final QueryApplicationPort queryApplicationPort;
    private final QueryAcceptancePort queryAcceptancePort;

    public TeacherQueryFieldTraineesAndContractWorkersResponse execute(Long companyId) {

        List<FieldTraineesVO> queryFieldTraineesVOs =
                queryApplicationPort.queryApplicationsFieldTraineesByCompanyId(companyId);

        List<Acceptance> acceptanceEntities =
                queryAcceptancePort.queryAcceptancesByCompanyIdAndYear(companyId, Year.now().getValue());

        return new TeacherQueryFieldTraineesAndContractWorkersResponse(
                buildFieldTrainees(queryFieldTraineesVOs),
                buildContractWorkers(acceptanceEntities)
        );
    }

    private List<TeacherQueryFieldTraineesResponse> buildFieldTrainees(
            List<FieldTraineesVO> queryFieldTraineesVOs
    ) {
        return queryFieldTraineesVOs.stream()
                .map(vo -> TeacherQueryFieldTraineesResponse
                        .builder()
                        .applicationId(vo.getApplicationId())
                        .studentGcn(
                                Student.processGcn(
                                        vo.getGrade(),
                                        vo.getClassRoom(),
                                        vo.getNumber()
                                )
                        )
                        .studentName(vo.getStudentName())
                        .startDate(vo.getStartDate())
                        .endDate(vo.getEndDate())
                        .build())
                .toList();
    }

    private List<TeacherQueryContractWorkersResponse> buildContractWorkers(
            List<Acceptance> acceptanceEntities
    ) {
        return acceptanceEntities
                .stream()
                .map(
                        acceptance -> TeacherQueryContractWorkersResponse
                                .builder()
                                .acceptanceId(acceptance.getId())
                                .studentGcn(acceptance.getStudentGcn())
                                .studentName(acceptance.getStudentName())
                                .contractDate(acceptance.getContractDate())
                                .build()
                )
                .toList();
    }
}
