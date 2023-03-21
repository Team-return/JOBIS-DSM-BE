package team.returm.jobis.domain.recruitment.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.recruitment.domain.enums.RecruitStatus;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.returm.jobis.domain.recruitment.exception.RecruitmentCannotDeleteException;
import team.returm.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.returm.jobis.domain.user.domain.User;
import team.returm.jobis.domain.user.domain.enums.Authority;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.Service;

@Service
@RequiredArgsConstructor
public class DeleteRecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final UserFacade userFacade;

    public void execute(UUID recruitmentId) {
        User user = userFacade.getCurrentUser();

        Recruitment recruitment = recruitmentRepository.queryRecruitmentById(recruitmentId)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        if (user.getAuthority() == Authority.COMPANY) {
            recruitment.checkCompany(user.getId());
        }

        if (recruitment.getStatus() == RecruitStatus.RECRUITING) {
            throw RecruitmentCannotDeleteException.EXCEPTION;
        }

        recruitmentRepository.deleteRecruitment(recruitment.getId());
    }
}
