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
import team.retum.jobis.domain.student.model.Student;

import java.time.Year;
import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class TeacherQueryFieldTraineesAndContractWorkersUseCase {

    private final QueryApplicationPort queryApplicationPort;
    private final QueryAcceptancePort queryAcceptancePort;

    public TeacherQueryFieldTraineesAndContractWorkersResponse execute(Long companyId) {
        List<FieldTraineesVO> fieldTrainees = queryApplicationPort.queryApplicationsFieldTraineesByCompanyId(companyId);
        List<AcceptanceVO> acceptances = queryAcceptancePort.queryAcceptancesByCompanyIdAndYear(companyId, Year.now().getValue());

        return new TeacherQueryFieldTraineesAndContractWorkersResponse(
                buildFieldTrainees(fieldTrainees),
                buildContractWorkers(acceptances)
        );
    }

    private List<TeacherQueryFieldTraineesResponse> buildFieldTrainees(
            List<FieldTraineesVO> fieldTrainees
    ) {
        return fieldTrainees.stream()
                .map(
                        fieldTrainee -> TeacherQueryFieldTraineesResponse
                                .builder()
                                .applicationId(fieldTrainee.getApplicationId())
                                .studentGcn(
                                        Student.processGcn(
                                                fieldTrainee.getGrade(),
                                                fieldTrainee.getClassRoom(),
                                                fieldTrainee.getNumber()
                                        )
                                )
                                .studentName(fieldTrainee.getStudentName())
                                .startDate(fieldTrainee.getStartDate())
                                .endDate(fieldTrainee.getEndDate())
                                .build()
                ).toList();
    }

    private List<TeacherQueryContractWorkersResponse> buildContractWorkers(
            List<AcceptanceVO> acceptances
    ) {
        return acceptances.stream()
                .map(
                        acceptance -> TeacherQueryContractWorkersResponse
                                .builder()
                                .acceptanceId(acceptance.getAcceptanceId())
                                .studentGcn(
                                        Student.processGcn(
                                                acceptance.getGrade(),
                                                acceptance.getClassRoom(),
                                                acceptance.getNumber()
                                        ))
                                .studentName(acceptance.getStudentName())
                                .contractDate(acceptance.getContractDate())
                                .build()
                ).toList();
    }
}
