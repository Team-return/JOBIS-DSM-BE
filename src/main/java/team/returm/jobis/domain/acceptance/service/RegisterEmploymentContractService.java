package team.returm.jobis.domain.acceptance.service;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.acceptance.domain.Acceptance;
import team.returm.jobis.domain.acceptance.domain.repository.AcceptanceRepository;
import team.returm.jobis.domain.acceptance.domain.repository.vo.ApplicationDetailVO;
import team.returm.jobis.domain.acceptance.presentation.dto.request.RegisterEmploymentContractRequest;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import team.returm.jobis.domain.application.domain.repository.ApplicationRepository;
import team.returm.jobis.domain.application.exception.ApplicationNotFoundException;
import team.returm.jobis.domain.application.exception.ApplicationStatusCannotChangeException;
import team.returm.jobis.domain.student.domain.Student;
import team.returm.jobis.global.annotation.Service;
import team.returm.jobis.global.util.StringUtil;

@Service
@RequiredArgsConstructor
public class RegisterEmploymentContractService {

    private final AcceptanceRepository acceptanceRepository;
    private final ApplicationRepository applicationRepository;

    public void execute(RegisterEmploymentContractRequest request) {
        List<ApplicationDetailVO> applications = applicationRepository.queryApplicationDetailsByIds(request.getApplicationIds());
        if(applications.size() != request.getApplicationIds().size()) {
            throw ApplicationNotFoundException.EXCEPTION;
        }

        String codeKeywords = StringUtil.joinStringList(request.getCodeKeywords());

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
                                    .tech(codeKeywords)
                                    .businessArea(100L) // TODO: 2023/04/20 rebase후 작업
                                    .build();
                        }
                ).toList();

        acceptanceRepository.saveAllAcceptance(acceptances);
    }
}
