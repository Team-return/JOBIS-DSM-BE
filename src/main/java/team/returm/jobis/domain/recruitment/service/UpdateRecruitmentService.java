package team.returm.jobis.domain.recruitment.service;

import team.returm.jobis.domain.company.domain.Company;
import team.returm.jobis.domain.company.facade.CompanyFacade;
import team.returm.jobis.domain.recruitment.presentation.dto.request.UpdateRecruitmentRequest;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.recruitment.facade.RecruitFacade;
import team.returm.jobis.domain.user.domain.User;
import team.returm.jobis.domain.user.domain.enums.Authority;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.Service;
import team.returm.jobis.global.util.StringUtil;
import lombok.RequiredArgsConstructor;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UpdateRecruitmentService {

    private final CompanyFacade companyFacade;
    private final RecruitFacade recruitFacade;
    private final UserFacade userFacade;

    public void execute(UpdateRecruitmentRequest request, UUID recruitmentId) {
        User user = userFacade.getCurrentUser();

        Recruitment recruitment = recruitFacade.queryRecruitmentById(recruitmentId);

        if (user.getAuthority() == Authority.COMPANY) {
            Company company = companyFacade.queryCompanyById(user.getId());
            recruitment.checkCompany(company.getId());
        }

        String requiredLicenses = StringUtil.getRequiredLicenses(request.getRequiredLicenses());
        String hiringProgress = StringUtil.getHiringProgress(request.getHiringProgress());

        recruitment.update(
                request.getTrainPay(), request.getPay(), request.getWorkHours(), request.getSubmitDocument(),
                request.getStartDate(), request.getEndDate(), request.getBenefits(), requiredLicenses,
                request.isMilitary(), request.getEtc(), request.getPreferentialTreatment(), hiringProgress,
                request.getRequiredGrade()
        );
    }
}
