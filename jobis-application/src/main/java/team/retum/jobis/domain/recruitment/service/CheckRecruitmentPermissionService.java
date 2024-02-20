package team.retum.jobis.domain.recruitment.service;

import team.retum.jobis.common.annotation.Service;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.recruitment.exception.CompanyMismatchException;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;
import team.retum.jobis.domain.user.model.User;

// 추수 COMPANY 다시 활성화되면 사용
@Service
public class CheckRecruitmentPermissionService {

    public void checkPermission(User currentUser, Recruitment recruitment) {
        if (currentUser.getAuthority() == Authority.TEACHER) {
            return;
        }

        if (currentUser.getAuthority() == Authority.COMPANY && !currentUser.getId().equals(recruitment.getCompanyId())) {
            throw CompanyMismatchException.EXCEPTION;
        }
    }

    public void checkPermission(User currentUser, RecruitArea recruitArea, QueryRecruitmentPort queryRecruitmentPort) {
        queryRecruitmentPort.queryRecruitmentById(recruitArea.getRecruitmentId())
                .ifPresent(recruitment -> checkPermission(currentUser, recruitment));
    }
}
