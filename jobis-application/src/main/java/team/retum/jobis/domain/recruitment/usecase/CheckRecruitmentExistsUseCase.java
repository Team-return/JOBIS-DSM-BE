package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.recruitment.spi.RecruitmentPort;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class CheckRecruitmentExistsUseCase {

    private final RecruitmentPort recruitmentPort;
    private final SecurityPort securityPort;

    public boolean execute(boolean winterIntern) {
        Long companyId = securityPort.getCurrentCompany().getId();
        return recruitmentPort.existsByCompanyIdAndWinterIntern(companyId, winterIntern);
    }
}
