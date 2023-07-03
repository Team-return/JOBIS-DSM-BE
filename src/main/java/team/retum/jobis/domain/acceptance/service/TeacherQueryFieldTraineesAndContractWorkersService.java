package team.retum.jobis.domain.acceptance.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.acceptance.domain.Acceptance;
import team.retum.jobis.domain.acceptance.domain.repository.AcceptanceRepository;
import team.retum.jobis.domain.acceptance.presentation.dto.response.TeacherQueryFieldTraineesAndContractWorkersResponse;
import team.retum.jobis.domain.acceptance.presentation.dto.response.TeacherQueryFieldTraineesAndContractWorkersResponse.TeacherQueryContractWorkersResponse;
import team.retum.jobis.domain.acceptance.presentation.dto.response.TeacherQueryFieldTraineesAndContractWorkersResponse.TeacherQueryFieldTraineesResponse;
import team.retum.jobis.domain.application.domain.repository.ApplicationRepository;
import team.retum.jobis.domain.application.domain.repository.vo.QueryFieldTraineesVO;
import team.retum.jobis.domain.student.domain.Student;
import team.retum.jobis.global.annotation.ReadOnlyService;

import java.time.Year;
import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class TeacherQueryFieldTraineesAndContractWorkersService {

    private final ApplicationRepository applicationRepository;
    private final AcceptanceRepository acceptanceRepository;

    public TeacherQueryFieldTraineesAndContractWorkersResponse execute(Long companyId) {

        List<QueryFieldTraineesVO> queryFieldTraineesVOs =
                applicationRepository.queryApplicationsFieldTraineesByCompanyId(companyId);

        List<Acceptance> acceptances =
                acceptanceRepository.queryAcceptancesByCompanyIdAndYear(companyId, Year.now().getValue());

        return new TeacherQueryFieldTraineesAndContractWorkersResponse(
                buildFieldTrainees(queryFieldTraineesVOs),
                buildContractWorkers(acceptances)
        );
    }

    private List<TeacherQueryFieldTraineesResponse> buildFieldTrainees(
            List<QueryFieldTraineesVO> queryFieldTraineesVOs
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
            List<Acceptance> acceptances
    ) {
        return acceptances
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