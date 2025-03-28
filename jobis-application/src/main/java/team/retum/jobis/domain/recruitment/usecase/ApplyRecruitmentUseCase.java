package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.PublishEventPort;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.intern.event.WinterInternRegisteredEvent;
import team.retum.jobis.domain.recruitment.dto.request.ApplyRecruitmentRequest;
import team.retum.jobis.domain.recruitment.event.InterestedRecruitmentEvent;
import team.retum.jobis.domain.recruitment.exception.RecruitmentAlreadyExistsException;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.CommandRecruitAreaPort;
import team.retum.jobis.domain.recruitment.spi.RecruitmentPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class ApplyRecruitmentUseCase {

    private final RecruitmentPort recruitmentPort;
    private final CommandRecruitAreaPort commandRecruitAreaPort;
    private final SecurityPort securityPort;
    private final PublishEventPort publishEventPort;

    public void execute(ApplyRecruitmentRequest request) {
        Company company = securityPort.getCurrentCompany();
        checkRecruitmentApplicable(
            company,
            request.winterIntern()
        );

        Recruitment recruitment = recruitmentPort.save(
            Recruitment.of(request, company.getId())
        );

        List<RecruitArea> recruitAreas = request.areas().stream()
            .map(area -> RecruitArea.of(area, recruitment.getId()))
            .toList();
        commandRecruitAreaPort.saveAll(recruitAreas);

        if (request.winterIntern()) {
            publishEventPort.publishEvent(new WinterInternRegisteredEvent(recruitment));
        }
    }

    public void executeInterestCodeMatch() {
        List<Recruitment> recentRecruitments = recruitmentPort.getRecent();

        for (Recruitment recruitment : recentRecruitments) {
            publishEventPort.publishEvent(new InterestedRecruitmentEvent(recruitment));
        }
    }

    private void checkRecruitmentApplicable(Company company, boolean isWinterIntern) {
        List<Recruitment> recruitments = recruitmentPort.getByCompanyIdAndWinterIntern(company.getId(), isWinterIntern);
        boolean onGoingRecruitment = recruitments.stream()
            .anyMatch(recruitment -> !recruitment.getStatus().equals(RecruitStatus.DONE));

        if (onGoingRecruitment) {
            throw RecruitmentAlreadyExistsException.EXCEPTION;
        }
    }
}
