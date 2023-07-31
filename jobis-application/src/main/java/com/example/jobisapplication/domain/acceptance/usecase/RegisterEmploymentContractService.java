package com.example.jobisapplication.domain.acceptance.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.domain.acceptance.dto.request.RegisterEmploymentContractRequest;
import com.example.jobisapplication.domain.acceptance.model.Acceptance;
import com.example.jobisapplication.domain.acceptance.spi.CommandAcceptancePort;
import com.example.jobisapplication.domain.application.spi.CommandApplicationPort;
import com.example.jobisapplication.domain.application.spi.QueryApplicationPort;
import com.example.jobisapplication.domain.application.spi.vo.ApplicationDetailVO;
import com.example.jobisapplication.domain.student.model.Student;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.application.model.ApplicationStatus;
import com.example.jobisapplication.domain.application.exception.ApplicationNotFoundException;
import com.example.jobisapplication.domain.application.exception.ApplicationStatusCannotChangeException;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@RequiredArgsConstructor
@UseCase
public class RegisterEmploymentContractService {

    private final CommandAcceptancePort commandAcceptancePort;
    private final QueryApplicationPort applicationRepository;
    private final CommandApplicationPort commandApplicationPort;

    public void execute(RegisterEmploymentContractRequest request) {
        List<ApplicationDetailVO> applications = applicationRepository.queryApplicationDetailsByIds(request.getApplicationIds());
        if (applications.size() != request.getApplicationIds().size()) {
            throw ApplicationNotFoundException.EXCEPTION;
        }

        List<Acceptance> acceptances = applications.stream()
                .map(
                        application -> {
                            if (application.getStatus() != ApplicationStatus.FIELD_TRAIN) {
                                throw ApplicationStatusCannotChangeException.EXCEPTION;
                            }

                            return Acceptance.builder()
                                    .studentName(application.getStudentName())
                                    .companyId(application.getId())
                                    .studentGcn(Student.processGcn(
                                            application.getStudentGrade(),
                                            application.getStudentClassNumber(),
                                            application.getStudentNumber()
                                    ))
                                    .contractDate(LocalDate.now())
                                    .year(Year.now().getValue())
                                    .tech(request.getCodeKeywords())
                                    .businessArea(application.getBusinessArea())
                                    .build();
                        }
                ).toList();

        commandAcceptancePort.saveAllAcceptance(acceptances);
        commandApplicationPort.deleteApplicationByIds(
                applications.stream().map(ApplicationDetailVO::getId).toList()
        );
    }
}
