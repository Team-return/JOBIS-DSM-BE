package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.PublishEventPort;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.intern.event.WinterInternRegisteredEvent;
import team.retum.jobis.domain.intern.event.WinterInternToggledEvent;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.recruitment.dto.request.ApplyRecruitmentRequest;
import team.retum.jobis.domain.recruitment.event.InterestedRecruitmentEvent;
import team.retum.jobis.domain.recruitment.exception.RecruitmentAlreadyExistsException;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.CommandRecruitAreaPort;
import team.retum.jobis.domain.recruitment.spi.RecruitmentPort;
import team.retum.jobis.domain.user.spi.QueryUserPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class ApplyRecruitmentUseCase {

    private final RecruitmentPort recruitmentPort;
    private final CommandRecruitAreaPort commandRecruitAreaPort;
    private final SecurityPort securityPort;
    private final PublishEventPort publishEventPort;
    private final QueryUserPort queryUserPort;

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

        List<String> deviceTokens = queryUserPort.getDeviceTokenByTopic(Topic.RECRUITMENT);
        if (request.winterIntern()) {
            publishEventPort.publishEvent(new WinterInternRegisteredEvent(
                recruitment,
                recruitment.getId(),
                Topic.WINTER_INTERN,
                deviceTokens
            ));
        }
    }

    public void executeInterestCodeMatch() {
        List<Recruitment> recentRecruitments = recruitmentPort.getRecent();
        List<String> deviceTokens = queryUserPort.getDeviceTokenByTopic(Topic.RECRUITMENT);

        for (Recruitment recruitment : recentRecruitments) {
            publishEventPort.publishEvent(new InterestedRecruitmentEvent(
                recruitment,
                List.of(recruitment.getId()),
                Topic.RECRUITMENT,
                deviceTokens
            ));
        }
    }

    private void checkRecruitmentApplicable(Company company, boolean isWinterIntern) {
        if (recruitmentPort.existsByCompanyIdAndWinterIntern(company.getId(), isWinterIntern)) {
            throw RecruitmentAlreadyExistsException.EXCEPTION;
        }
    }
}
