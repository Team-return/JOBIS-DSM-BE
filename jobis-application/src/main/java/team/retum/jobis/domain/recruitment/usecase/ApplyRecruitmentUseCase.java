package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.company.model.Company;
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
    private final SecurityPort securityPort;

    public void execute(ApplyRecruitmentRequest request) {
        Company company = securityPort.getCurrentCompany();
        checkRecruitmentApplicableService.checkRecruitmentApplicable(
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
}
