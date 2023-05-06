package team.returm.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.company.domain.Company;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.recruitment.domain.enums.RecruitStatus;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.returm.jobis.domain.recruitment.domain.service.RecruitAreaService;
import team.returm.jobis.domain.recruitment.presentation.dto.request.ApplyRecruitmentRequest;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.Service;
import team.returm.jobis.global.util.StringUtil;

import java.time.Year;

@RequiredArgsConstructor
@Service
public class ApplyRecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final UserFacade userFacade;
    private final RecruitAreaService recruitAreaService;

    public void execute(ApplyRecruitmentRequest request) {
        Company company = userFacade.getCurrentCompany();

        String hiringProgress = StringUtil.getHiringProgress(request.getHiringProgress());
        String requiredLicenses = StringUtil.joinStringList(request.getRequiredLicenses());

        Recruitment recruitment = recruitmentRepository.saveRecruitment(
                Recruitment.builder()
                        .company(company)
                        .recruitYear(Year.now().getValue())
                        .militarySupport(request.isMilitarySupport())
                        .workingHours(request.getWorkHours())
                        .preferentialTreatment(request.getPreferentialTreatment())
                        .requiredLicenses(requiredLicenses)
                        .status(RecruitStatus.REQUESTED)
                        .requiredGrade(request.getRequiredGrade())
                        .startDate(request.getStartDate())
                        .endDate(request.getEndDate())
                        .trainPay(request.getTrainPay())
                        .submitDocument(request.getSubmitDocument())
                        .pay(request.getPay())
                        .benefits(request.getBenefits())
                        .etc(request.getEtc())
                        .hiringProgress(hiringProgress)
                        .build()
        );

        request.getAreas()
                .forEach(area ->
                        recruitAreaService.createRecruitArea(area, recruitment)
                );
    }
}
