package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.recruitment.dto.request.ApplyRecruitmentRequest;
import team.retum.jobis.domain.recruitment.exception.RecruitmentAlreadyExistsException;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.CommandRecruitmentPort;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class ApplyRecruitmentUseCase {

    private final CommandRecruitmentPort commandRecruitmentPort;
    private final QueryRecruitmentPort queryRecruitmentPort;
    private final SecurityPort securityPort;

    public void execute(ApplyRecruitmentRequest request) {
        Company company = securityPort.getCurrentCompany();

        checkRecruitmentApplicable(
            company,
            request.winterIntern()
        );

        Recruitment recruitment = commandRecruitmentPort.saveRecruitment(
            Recruitment.of(request, company.getId())
        );

        List<RecruitArea> recruitAreas = request.areas().stream()
            .map(area -> RecruitArea.of(area, recruitment.getId()))
            .toList();
        commandRecruitmentPort.saveAllRecruitmentAreas(recruitAreas);
    }

    private void checkRecruitmentApplicable(Company company, boolean isWinterIntern) {
        if (queryRecruitmentPort.existsOnRecruitmentByCompanyIdAndWinterIntern(company.getId(), isWinterIntern)) {
            throw RecruitmentAlreadyExistsException.EXCEPTION;
        }
    }
}
