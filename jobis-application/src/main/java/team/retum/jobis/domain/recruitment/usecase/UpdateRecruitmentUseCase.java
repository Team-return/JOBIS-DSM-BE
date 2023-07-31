package team.retum.jobis.domain.recruitment.usecase;

import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.recruitment.dto.request.UpdateRecruitmentRequest;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class UpdateRecruitmentUseCase {

    private final SecurityPort securityPort;
    private final QueryRecruitmentPort queryRecruitmentPort;

    public void execute(UpdateRecruitmentRequest request, Long recruitmentId) {
        Recruitment recruitment = queryRecruitmentPort.queryRecruitmentById(recruitmentId)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        Authority currentUserAuthority = securityPort.getCurrentUserAuthority();
        if (currentUserAuthority == Authority.COMPANY) {
            Long currentUserId = securityPort.getCurrentUserId();
            recruitment.checkCompany(currentUserId);
        }


        recruitment.update(
                request.getTrainPay(), request.getPay(), request.getWorkHours(), request.getSubmitDocument(),
                request.getStartDate(), request.getEndDate(), request.getBenefits(), request.getRequiredLicenses(),
                request.isMilitary(), request.getEtc(), request.getPreferentialTreatment(), request.getHiringProgress(),
                request.getRequiredGrade()
        );
    }
}
