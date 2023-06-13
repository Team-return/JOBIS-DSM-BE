package team.returm.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.returm.jobis.domain.recruitment.domain.repository.vo.RecruitAreaVO;
import team.returm.jobis.domain.recruitment.presentation.dto.response.QueryMyRecruitmentResponse;
import team.returm.jobis.domain.recruitment.presentation.dto.response.RecruitAreaResponse;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.ReadOnlyService;
import team.returm.jobis.global.util.StringUtil;

import java.util.List;

@ReadOnlyService
@RequiredArgsConstructor
public class QueryMyRecruitmentService {

    private final UserFacade userFacade;
    private final RecruitmentRepository recruitmentRepository;

    public QueryMyRecruitmentResponse execute() {
        Long currentUserId = userFacade.getCurrentUserId();

        Recruitment recruitment =
                recruitmentRepository.queryRecentRecruitmentByCompanyId(currentUserId);

        List<RecruitAreaVO> recruitAreas =
                recruitmentRepository.queryRecruitAreasByRecruitmentId(recruitment.getId());

        return QueryMyRecruitmentResponse.builder()
                .recruitmentId(recruitment.getId())
                .recruitYear(recruitment.getRecruitYear())
                .areas(RecruitAreaResponse.of(recruitAreas))
                .preferentialTreatment(recruitment.getPreferentialTreatment())
                .requiredGrade(recruitment.getRequiredGrade())
                .requiredLicenses(recruitment.getRequiredLicenses())
                .workingHours(recruitment.getWorkingHours())
                .trainingPay(recruitment.getPayInfo().getTrainingPay())
                .pay(recruitment.getPayInfo().getPay())
                .benefits(recruitment.getBenefits())
                .militarySupport(recruitment.getMilitarySupport())
                .hiringProgress(recruitment.getHiringProgress())
                .startDate(recruitment.getRecruitDate().getStartDate())
                .endDate(recruitment.getRecruitDate().getFinishDate())
                .etc(recruitment.getEtc())
                .build();
    }
}
