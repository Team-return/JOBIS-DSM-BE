package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.recruitment.spi.RecruitmentPort;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class CheckRecruitmentExistsUseCase {

    private final RecruitmentPort recruitmentPort;

    public boolean execute(Long companyId) {
        return recruitmentPort.existsByCompanyId(companyId);
    }
}
