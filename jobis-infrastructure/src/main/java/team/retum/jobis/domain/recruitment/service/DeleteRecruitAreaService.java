package team.retum.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitAreaEntity;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentRepository;
import team.retum.jobis.domain.recruitment.exception.RecruitAreaNotFoundException;
import team.retum.jobis.domain.user.persistence.entity.UserEntity;
import com.example.jobisapplication.domain.auth.model.Authority;
import team.retum.jobis.domain.user.facade.UserFacade;
import com.example.jobisapplication.common.annotation.Service;

@Service
@RequiredArgsConstructor
public class DeleteRecruitAreaService {

    private final UserFacade userFacade;
    private final RecruitmentRepository recruitmentRepository;

    public void execute(Long recruitAreaId) {
        UserEntity userEntity = userFacade.getCurrentUser();

        RecruitAreaEntity recruitAreaEntity = recruitmentRepository.queryRecruitAreaById(recruitAreaId)
                .orElseThrow(() -> RecruitAreaNotFoundException.EXCEPTION);
        RecruitmentEntity recruitmentEntity = recruitAreaEntity.getRecruitmentEntity();

        if (recruitmentEntity.getRecruitAreaEntities().size() <= 1) {
            throw RecruitAreaCannotDeleteException.EXCEPTION;
        }

        if (userEntity.getAuthority() == Authority.COMPANY) {
            recruitmentEntity.checkCompany(userEntity.getId());
        }

        recruitmentRepository.deleteRecruitAreaById(recruitAreaEntity.getId());
    }
}
