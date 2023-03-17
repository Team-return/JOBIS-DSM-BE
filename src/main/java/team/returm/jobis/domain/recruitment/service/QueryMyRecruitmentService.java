package team.returm.jobis.domain.recruitment.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.returm.jobis.domain.recruitment.facade.RecruitFacade;
import team.returm.jobis.domain.recruitment.presentation.dto.response.QueryMyRecruitmentResponse;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.ReadOnlyService;
import team.returm.jobis.global.util.StringUtil;

@ReadOnlyService
@RequiredArgsConstructor
public class QueryMyRecruitmentService {

    private final UserFacade userFacade;
    private final RecruitmentRepository recruitmentRepository;
    private final RecruitFacade recruitFacade;

    public QueryMyRecruitmentResponse execute() {
        UUID currentUserId = userFacade.getCurrentUserId();

        Recruitment recruitment =
                recruitmentRepository.queryRecentRecruitmentByCompanyId(currentUserId);

        return QueryMyRecruitmentResponse.builder()
                .recruitmentId(recruitment.getId())
                .recruitYear(recruitment.getRecruitYear())
                .areas(
                        recruitFacade.queryRecruitAreas(recruitment.getId())
                )
                .preferentialTreatment(recruitment.getPreferentialTreatment())
                .requiredGrade(recruitment.getRequiredGrade())
                .requiredLicenses(StringUtil.divideString(
                        recruitment.getRequiredLicenses()
                ))
                .workingHours(recruitment.getWorkingHours())
                .trainingPay(recruitment.getPayInfo().getTrainingPay())
                .pay(recruitment.getPayInfo().getPay())
                .benefits(recruitment.getBenefits())
                .militarySupport(recruitment.getMilitarySupport())
                .hiringProgress(StringUtil.divideString(
                        recruitment.getHiringProgress()
                ))
                .startDate(recruitment.getRecruitDate().getStartDate())
                .endDate(recruitment.getRecruitDate().getFinishDate())
                .etc(recruitment.getEtc())
                .build();
    }
}
