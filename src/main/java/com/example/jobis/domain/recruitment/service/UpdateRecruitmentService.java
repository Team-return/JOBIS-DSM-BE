package com.example.jobis.domain.recruitment.service;

import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.facade.CompanyFacade;
import com.example.jobis.domain.recruitment.presentation.dto.request.UpdateRecruitmentRequest;
import com.example.jobis.domain.recruitment.domain.Recruitment;
import com.example.jobis.domain.recruitment.facade.RecruitFacade;
import com.example.jobis.domain.user.domain.User;
import com.example.jobis.domain.user.domain.enums.Authority;
import com.example.jobis.domain.user.facade.UserFacade;
import com.example.jobis.global.annotation.Service;
import com.example.jobis.global.util.StringUtil;
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
