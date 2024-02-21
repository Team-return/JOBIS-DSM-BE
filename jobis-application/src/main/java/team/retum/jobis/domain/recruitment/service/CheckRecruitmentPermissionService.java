package team.retum.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.Service;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.recruitment.exception.CompanyMismatchException;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;
import team.retum.jobis.domain.user.model.User;

// 추후 COMPANY 다시 활성화되면 사용
@RequiredArgsConstructor
@Service
public class CheckRecruitmentPermissionService {
    private final QueryRecruitmentPort queryRecruitmentPort;

    public void checkPermission(User currentUser, Recruitment recruitment) {
        if (currentUser.getAuthority() == Authority.TEACHER) {
            return;
        }

        if (currentUser.getAuthority() == Authority.COMPANY && !currentUser.getId().equals(recruitment.getCompanyId())) {
            throw CompanyMismatchException.EXCEPTION;
        }
    }

    public void checkPermission(User currentUser, RecruitArea recruitArea) {
        queryRecruitmentPort.queryRecruitmentById(recruitArea.getRecruitmentId())
                .ifPresent(recruitment -> checkPermission(currentUser, recruitment));
    }
}
