package team.returm.jobis.domain.acceptance.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.acceptance.domain.Acceptance;
import team.returm.jobis.domain.acceptance.domain.repository.AcceptanceRepository;
import team.returm.jobis.domain.application.domain.repository.ApplicationRepository;
import team.returm.jobis.domain.acceptance.presentation.dto.response.TeacherQueryFieldTraineesAndContractWorkersResponse;
import team.returm.jobis.domain.acceptance.presentation.dto.response.TeacherQueryFieldTraineesAndContractWorkersResponse.TeacherQueryContractWorkersResponse;
import team.returm.jobis.domain.acceptance.presentation.dto.response.TeacherQueryFieldTraineesAndContractWorkersResponse.TeacherQueryFieldTraineesResponse;
import team.returm.jobis.domain.application.domain.repository.vo.QueryFieldTraineesVO;
import team.returm.jobis.domain.student.domain.Student;
import team.returm.jobis.global.annotation.ReadOnlyService;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class TeacherQueryFieldTraineesAndContractWorkersService {

    private final ApplicationRepository applicationRepository;
    private final AcceptanceRepository acceptanceRepository;

    public TeacherQueryFieldTraineesAndContractWorkersResponse execute(Long companyId) {

        List<Acceptance> acceptances = acceptanceRepository.queryAcceptancesByCompanyId(companyId);

        List<QueryFieldTraineesVO> queryFieldTraineesVOs = applicationRepository.queryApplicationsFieldTraineesByCompanyId(companyId);

        return new TeacherQueryFieldTraineesAndContractWorkersResponse(
                queryFieldTraineesVOs.stream()
                        .map(vo -> TeacherQueryFieldTraineesResponse
                                .builder()
                                .studentGcn(
                                        Student.processGcn(
                                                vo.getGrade(), vo.getClassRoom(), vo.getNumber()
                                        )
                                )
                                .studentName(vo.getStudentName())
                                .startDate(vo.getStartDate())
                                .endDate(vo.getEndDate())
                                .build())
                        .toList(),
                buildContractWorkers(acceptances)
        );
    }

    private List<TeacherQueryContractWorkersResponse> buildContractWorkers(List<Acceptance> acceptances) {
        return acceptances
                .stream()
                .map(
                        acceptance -> TeacherQueryContractWorkersResponse
                                .builder()
                                .studentGcn(acceptance.getStudentGcn())
                                .studentName(acceptance.getStudentName())
                                .contractDate(acceptance.getContractDate())
                                .build()
                )
                .toList();
    }
}
