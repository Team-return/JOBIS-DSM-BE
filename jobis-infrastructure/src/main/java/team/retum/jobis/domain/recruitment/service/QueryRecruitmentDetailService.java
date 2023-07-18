package team.retum.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.recruitment.persistence.Recruitment;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentRepository;
import team.retum.jobis.domain.recruitment.persistence.repository.vo.RecruitAreaVO;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.recruitment.presentation.dto.response.QueryRecruitmentDetailResponse;
import team.retum.jobis.domain.recruitment.presentation.dto.response.RecruitAreaResponse;
import team.retum.jobis.global.annotation.ReadOnlyService;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryRecruitmentDetailService {

    private final RecruitmentRepository recruitmentRepository;

    public QueryRecruitmentDetailResponse execute(Long recruitId) {
        Recruitment recruitment = recruitmentRepository.queryRecruitmentById(recruitId)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        List<RecruitAreaVO> recruitAreas =
                recruitmentRepository.queryRecruitAreasByRecruitmentId(recruitment.getId());

        return QueryRecruitmentDetailResponse.builder()
                .companyId(recruitment.getCompany().getId())
                .companyProfileUrl(recruitment.getCompany().getCompanyLogoUrl())
                .companyName(recruitment.getCompany().getName())
                .areas(RecruitAreaResponse.of(recruitAreas))
                .pay(recruitment.getPayInfo().getPay())
                .trainPay(recruitment.getPayInfo().getTrainPay())
                .etc(recruitment.getEtc())
                .submitDocument(recruitment.getSubmitDocument())
                .requiredGrade(recruitment.getRequiredGrade())
                .requiredLicenses(recruitment.getRequiredLicenses())
                .startDate(recruitment.getRecruitDate().getStartDate())
                .endDate(recruitment.getRecruitDate().getFinishDate())
                .hiringProgress(recruitment.getHiringProgress())
                .workHours(recruitment.getWorkingHours())
                .preferentialTreatment(recruitment.getPreferentialTreatment())
                .military(recruitment.getMilitarySupport())
                .benefits(recruitment.getBenefits())
                .build();
    }
}
