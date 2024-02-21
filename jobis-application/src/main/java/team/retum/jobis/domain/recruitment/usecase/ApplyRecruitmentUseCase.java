package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;
import team.retum.jobis.domain.recruitment.dto.request.ApplyRecruitmentRequest;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.service.CheckRecruitmentApplicableService;
import team.retum.jobis.domain.recruitment.spi.CommandRecruitmentPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class ApplyRecruitmentUseCase {
    private final CommandRecruitmentPort commandRecruitmentPort;
    private final CheckRecruitmentApplicableService checkRecruitmentApplicableService;
    private final QueryCompanyPort queryCompanyPort;

    public void execute(ApplyRecruitmentRequest request, Long companyId) {
        Company company = queryCompanyPort.queryCompanyById(companyId)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);
        checkRecruitmentApplicableService.checkRecruitmentApplicable(
                company,
                request.isWinterIntern()
        );

        Recruitment recruitment = commandRecruitmentPort.saveRecruitment(
                Recruitment.of(request, companyId)
        );

        List<RecruitArea> recruitAreas = request.getAreas().stream()
                .map(area -> RecruitArea.of(area, recruitment.getId()))
                .toList();
        commandRecruitmentPort.saveAllRecruitmentAreas(recruitAreas);
    }
}
