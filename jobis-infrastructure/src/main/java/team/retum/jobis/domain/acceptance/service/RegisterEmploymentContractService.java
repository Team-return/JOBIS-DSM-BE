package team.retum.jobis.domain.acceptance.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.acceptance.persistence.Acceptance;
import team.retum.jobis.domain.acceptance.persistence.repository.AcceptanceRepository;
import team.retum.jobis.domain.acceptance.persistence.repository.vo.ApplicationDetailVO;
import team.retum.jobis.domain.acceptance.presentation.dto.request.RegisterEmploymentContractRequest;
import team.retum.jobis.domain.application.persistence.enums.ApplicationStatus;
import team.retum.jobis.domain.application.persistence.repository.ApplicationRepository;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
import team.retum.jobis.domain.application.exception.ApplicationStatusCannotChangeException;
import team.retum.jobis.domain.student.persistence.Student;
import team.retum.jobis.global.annotation.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterEmploymentContractService {

    private final AcceptanceRepository acceptanceRepository;
    private final ApplicationRepository applicationRepository;

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
                                    .company(application.getCompany())
                                    .studentGcn(Student.processGcn(
                                            application.getStudentGrade(),
                                            application.getStudentClassNumber(),
                                            application.getStudentNumber()
                                    ))
                                    .contractDate(LocalDate.now())
                                    .year(Year.now().getValue())
                                    .tech(request.getCodeKeywords())
                                    .businessArea(application.getCompany().getBusinessArea())
                                    .build();
                        }
                ).toList();

        acceptanceRepository.saveAllAcceptance(acceptances);
        applicationRepository.deleteApplicationByIds(
                applications.stream().map(ApplicationDetailVO::getId).toList()
        );
    }
}
