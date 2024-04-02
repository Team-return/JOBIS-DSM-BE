package team.retum.jobis.domain.recruitment.checker;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.Service;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.recruitment.exception.CompanyMismatchException;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

@RequiredArgsConstructor
@Service
public class RecruitmentChecker {

    private final QueryRecruitmentPort queryRecruitmentPort;
    private final SecurityPort securityPort;

    public void checkPermission(Recruitment recruitment) {
        if (securityPort.getCurrentUserAuthority() == Authority.TEACHER) {
            return;
        }

        if (!securityPort.getCurrentCompany().getId().equals(recruitment.getCompanyId())) {
            throw CompanyMismatchException.EXCEPTION;
        }
    }

    public void checkPermission(RecruitArea recruitArea) {
        queryRecruitmentPort.queryRecruitmentById(recruitArea.getRecruitmentId())
            .ifPresent(this::checkPermission);
    }
}
