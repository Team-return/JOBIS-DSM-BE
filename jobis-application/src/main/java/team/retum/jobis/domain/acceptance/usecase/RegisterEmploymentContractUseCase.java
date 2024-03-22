package team.retum.jobis.domain.acceptance.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.acceptance.model.Acceptance;
import team.retum.jobis.domain.acceptance.spi.CommandAcceptancePort;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
import team.retum.jobis.domain.application.exception.ApplicationStatusCannotChangeException;
import team.retum.jobis.domain.application.model.Application;
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

    public void execute(List<String> codeKeywords, List<Long> applicationIds) {
        List<ApplicationDetailVO> applications = applicationRepository.queryApplicationDetailsByIds(applicationIds);
        if (applications.size() != applicationIds.size()) {
            throw ApplicationNotFoundException.EXCEPTION;
        }

        List<Acceptance> acceptances = applications.stream()
                .map(
                        application -> {
                            Application.checkApplicationStatus(
                                    application.getStatus(),
                                    ApplicationStatus.FIELD_TRAIN
                            );

                            return Acceptance.builder()
                                    .companyId(application.getCompanyId())
                                    .contractDate(LocalDate.now())
                                    .year(Year.now().getValue())
                                    .tech(codeKeywords)
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
