package team.retum.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;
import team.retum.jobis.domain.recruitment.facade.RecruitmentFacade;
import team.retum.jobis.domain.recruitment.presentation.dto.request.UpdateRecruitmentWebRequest;
import team.retum.jobis.domain.user.persistence.entity.UserEntity;
import com.example.jobisapplication.domain.auth.model.Authority;
import team.retum.jobis.domain.user.facade.UserFacade;
import com.example.jobisapplication.common.annotation.Service;

@RequiredArgsConstructor
@Service
public class UpdateRecruitmentService {

    private final RecruitmentFacade recruitmentFacade;
    private final UserFacade userFacade;

    public void execute(UpdateRecruitmentWebRequest request, Long recruitmentId) {
        UserEntity userEntity = userFacade.getCurrentUser();

        RecruitmentEntity recruitmentEntity = recruitmentFacade.queryRecruitmentById(recruitmentId);
        if (userEntity.getAuthority() == Authority.COMPANY) {
            recruitmentEntity.checkCompany(userEntity.getId());
        }


        recruitmentEntity.update(
                request.getTrainPay(), request.getPay(), request.getWorkHours(), request.getSubmitDocument(),
                request.getStartDate(), request.getEndDate(), request.getBenefits(), request.getRequiredLicenses(),
                request.isMilitary(), request.getEtc(), request.getPreferentialTreatment(), request.getHiringProgress(),
                request.getRequiredGrade()
        );
    }
}
