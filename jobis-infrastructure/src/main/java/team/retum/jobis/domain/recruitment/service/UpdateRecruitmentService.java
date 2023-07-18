package team.retum.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.recruitment.persistence.Recruitment;
import team.retum.jobis.domain.recruitment.facade.RecruitmentFacade;
import team.retum.jobis.domain.recruitment.presentation.dto.request.UpdateRecruitmentRequest;
import team.retum.jobis.domain.user.persistence.User;
import com.example.jobisapplication.domain.auth.Authority;
import team.retum.jobis.domain.user.facade.UserFacade;
import com.example.jobisapplication.common.annotation.Service;

@RequiredArgsConstructor
@Service
public class UpdateRecruitmentService {

    private final RecruitmentFacade recruitmentFacade;
    private final UserFacade userFacade;

    public void execute(UpdateRecruitmentRequest request, Long recruitmentId) {
        User user = userFacade.getCurrentUser();

        Recruitment recruitment = recruitmentFacade.queryRecruitmentById(recruitmentId);
        if (user.getAuthority() == Authority.COMPANY) {
            recruitment.checkCompany(user.getId());
        }


        recruitment.update(
                request.getTrainPay(), request.getPay(), request.getWorkHours(), request.getSubmitDocument(),
                request.getStartDate(), request.getEndDate(), request.getBenefits(), request.getRequiredLicenses(),
                request.isMilitary(), request.getEtc(), request.getPreferentialTreatment(), request.getHiringProgress(),
                request.getRequiredGrade()
        );
    }
}
