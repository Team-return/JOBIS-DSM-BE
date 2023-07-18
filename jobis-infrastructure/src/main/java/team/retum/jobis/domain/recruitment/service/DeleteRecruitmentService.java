package team.retum.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.recruitment.persistence.Recruitment;
import team.retum.jobis.domain.recruitment.persistence.enums.RecruitStatus;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentRepository;
import team.retum.jobis.domain.recruitment.exception.RecruitmentCannotDeleteException;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.user.persistence.User;
import com.example.jobisapplication.domain.auth.Authority;
import team.retum.jobis.domain.user.facade.UserFacade;
import com.example.jobisapplication.common.annotation.Service;

@Service
@RequiredArgsConstructor
public class DeleteRecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final UserFacade userFacade;

    public void execute(Long recruitmentId) {
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
