package team.retum.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;
import com.example.jobisapplication.domain.recruitment.domain.RecruitStatus;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentRepository;
import team.retum.jobis.domain.recruitment.exception.RecruitmentCannotDeleteException;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.user.persistence.entity.UserEntity;
import com.example.jobisapplication.domain.auth.domain.Authority;
import team.retum.jobis.domain.user.facade.UserFacade;
import com.example.jobisapplication.common.annotation.Service;

@Service
@RequiredArgsConstructor
public class DeleteRecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final UserFacade userFacade;

    public void execute(Long recruitmentId) {
        UserEntity userEntity = userFacade.getCurrentUser();

        RecruitmentEntity recruitmentEntity = recruitmentRepository.queryRecruitmentById(recruitmentId)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        if (userEntity.getAuthority() == Authority.COMPANY) {
            recruitmentEntity.checkCompany(userEntity.getId());
        }

        if (recruitmentEntity.getStatus() == RecruitStatus.RECRUITING) {
            throw RecruitmentCannotDeleteException.EXCEPTION;
        }

        recruitmentRepository.deleteRecruitment(recruitmentEntity.getId());
    }
}
