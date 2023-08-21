package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.recruitment.dto.request.CreateRecruitAreaRequest;
import team.retum.jobis.domain.recruitment.exception.RecruitAreaNotFoundException;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.service.SaveRecruitmentAreaService;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

@RequiredArgsConstructor
@UseCase
public class CreateRecruitAreaUseCase {

    private final QueryRecruitmentPort queryRecruitmentPort;
    private final SaveRecruitmentAreaService saveRecruitmentAreaService;
    private final SecurityPort securityPort;

    public void execute(CreateRecruitAreaRequest request, Long recruitmentId) {
        Recruitment recruitment = queryRecruitmentPort.queryRecruitmentById(recruitmentId)
                .orElseThrow(() -> RecruitAreaNotFoundException.EXCEPTION);

        Authority currentUserAuthority = securityPort.getCurrentUserAuthority();
        if (currentUserAuthority == Authority.COMPANY) {
            recruitment.checkCompany(securityPort.getCurrentUserId());
        }

        saveRecruitmentAreaService.execute(request, recruitmentId);
    }
}
