package team.retum.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentRepository;
import team.retum.jobis.domain.recruitment.persistence.repository.vo.RecruitAreaVO;
import com.example.jobisapplication.domain.recruitment.exception.RecruitmentNotFoundException;
import com.example.jobisapplication.domain.recruitment.dto.response.QueryMyRecruitmentResponse;
import com.example.jobisapplication.domain.recruitment.dto.response.RecruitAreaResponse;
import team.retum.jobis.domain.user.facade.UserFacade;
import com.example.jobisapplication.common.annotation.ReadOnlyService;

import java.util.List;

@ReadOnlyService
@RequiredArgsConstructor
public class QueryMyRecruitmentService {

    private final UserFacade userFacade;
    private final RecruitmentRepository recruitmentRepository;

    public QueryMyRecruitmentResponse execute() {
        Long currentUserId = userFacade.getCurrentUserId();

        RecruitmentEntity recruitmentEntity = recruitmentRepository.queryRecentRecruitmentByCompanyId(currentUserId)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        List<RecruitAreaVO> recruitAreas =
                recruitmentRepository.queryRecruitAreasByRecruitmentId(recruitmentEntity.getId());

        return QueryMyRecruitmentResponse.builder()
                .recruitmentId(recruitmentEntity.getId())
                .recruitYear(recruitmentEntity.getRecruitYear())
                .areas(RecruitAreaResponse.of(recruitAreas))
                .preferentialTreatment(recruitmentEntity.getPreferentialTreatment())
                .requiredGrade(recruitmentEntity.getRequiredGrade())
                .requiredLicenses(recruitmentEntity.getRequiredLicenses())
                .workHours(recruitmentEntity.getWorkingHours())
                .trainPay(recruitmentEntity.getPayInfo().getTrainPay())
                .pay(recruitmentEntity.getPayInfo().getPay())
                .benefits(recruitmentEntity.getBenefits())
                .militarySupport(recruitmentEntity.getMilitarySupport())
                .hiringProgress(recruitmentEntity.getHiringProgress())
                .startDate(recruitmentEntity.getRecruitDate().getStartDate())
                .endDate(recruitmentEntity.getRecruitDate().getFinishDate())
                .etc(recruitmentEntity.getEtc())
                .build();
    }
}
