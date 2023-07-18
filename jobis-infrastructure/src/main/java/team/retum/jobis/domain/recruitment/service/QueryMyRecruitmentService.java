package team.retum.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.recruitment.domain.Recruitment;
import team.retum.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.retum.jobis.domain.recruitment.domain.repository.vo.RecruitAreaVO;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.recruitment.presentation.dto.response.QueryMyRecruitmentResponse;
import team.retum.jobis.domain.recruitment.presentation.dto.response.RecruitAreaResponse;
import team.retum.jobis.domain.user.facade.UserFacade;
import team.retum.jobis.global.annotation.ReadOnlyService;

import java.util.List;

@ReadOnlyService
@RequiredArgsConstructor
public class QueryMyRecruitmentService {

    private final UserFacade userFacade;
    private final RecruitmentRepository recruitmentRepository;

    public QueryMyRecruitmentResponse execute() {
        Long currentUserId = userFacade.getCurrentUserId();

        Recruitment recruitment = recruitmentRepository.queryRecentRecruitmentByCompanyId(currentUserId)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        List<RecruitAreaVO> recruitAreas =
                recruitmentRepository.queryRecruitAreasByRecruitmentId(recruitment.getId());

        return QueryMyRecruitmentResponse.builder()
                .recruitmentId(recruitment.getId())
                .recruitYear(recruitment.getRecruitYear())
                .areas(RecruitAreaResponse.of(recruitAreas))
                .preferentialTreatment(recruitment.getPreferentialTreatment())
                .requiredGrade(recruitment.getRequiredGrade())
                .requiredLicenses(recruitment.getRequiredLicenses())
                .workHours(recruitment.getWorkingHours())
                .trainPay(recruitment.getPayInfo().getTrainPay())
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
