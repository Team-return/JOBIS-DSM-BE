package com.example.jobisapplication.domain.acceptance.usecase;

import com.example.jobisapplication.common.annotation.ReadOnlyUseCase;
import com.example.jobisapplication.domain.acceptance.model.Acceptance;
import com.example.jobisapplication.domain.acceptance.spi.QueryAcceptancePort;
import com.example.jobisapplication.domain.application.spi.QueryApplicationPort;
import com.example.jobisapplication.domain.application.spi.vo.FieldTraineesVO;
import com.example.jobisapplication.domain.student.model.Student;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.acceptance.dto.response.TeacherQueryFieldTraineesAndContractWorkersResponse;
import com.example.jobisapplication.domain.acceptance.dto.response.TeacherQueryFieldTraineesAndContractWorkersResponse.TeacherQueryContractWorkersResponse;
import com.example.jobisapplication.domain.acceptance.dto.response.TeacherQueryFieldTraineesAndContractWorkersResponse.TeacherQueryFieldTraineesResponse;

import java.time.Year;
import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class TeacherQueryFieldTraineesAndContractWorkersService {

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
