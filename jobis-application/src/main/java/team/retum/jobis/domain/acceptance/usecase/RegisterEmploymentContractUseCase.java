package team.retum.jobis.domain.acceptance.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.acceptance.dto.request.RegisterEmploymentContractRequest;
import team.retum.jobis.domain.acceptance.model.Acceptance;
import team.retum.jobis.domain.acceptance.spi.CommandAcceptancePort;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
import team.retum.jobis.domain.application.exception.ApplicationStatusCannotChangeException;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.CommandApplicationPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.application.spi.vo.ApplicationDetailVO;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@RequiredArgsConstructor
@UseCase
public class RegisterEmploymentContractUseCase {

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
                                    .companyId(application.getCompanyId())
                                    .contractDate(LocalDate.now())
                                    .year(Year.now().getValue())
                                    .tech(request.getCodeKeywords())
                                    .businessArea(application.getBusinessArea())
                                    .studentId(application.getStudentId())
                                    .build();
                        }
                ).toList();

        commandAcceptancePort.saveAllAcceptance(acceptances);
        commandApplicationPort.changeApplicationStatus(
                ApplicationStatus.ACCEPTANCE,
                applications.stream().map(ApplicationDetailVO::getId).toList()
        );
    }
}
