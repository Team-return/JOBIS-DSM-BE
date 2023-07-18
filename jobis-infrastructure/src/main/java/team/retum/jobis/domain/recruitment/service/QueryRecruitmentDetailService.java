package team.retum.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentRepository;
import team.retum.jobis.domain.recruitment.persistence.repository.vo.QueryRecruitAreaVO;
import com.example.jobisapplication.domain.recruitment.exception.RecruitmentNotFoundException;
import com.example.jobisapplication.domain.recruitment.dto.response.QueryRecruitmentDetailResponse;
import com.example.jobisapplication.domain.recruitment.dto.response.RecruitAreaResponse;
import com.example.jobisapplication.common.annotation.ReadOnlyService;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryRecruitmentDetailService {

    private final RecruitmentRepository recruitmentRepository;

    public QueryRecruitmentDetailResponse execute(Long recruitId) {
        RecruitmentEntity recruitmentEntity = recruitmentRepository.queryRecruitmentById(recruitId)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        List<QueryRecruitAreaVO> recruitAreas =
                recruitmentRepository.queryRecruitAreasByRecruitmentId(recruitmentEntity.getId());

        return QueryRecruitmentDetailResponse.builder()
                .companyId(recruitmentEntity.getCompanyEntity().getId())
                .companyProfileUrl(recruitmentEntity.getCompanyEntity().getCompanyLogoUrl())
                .companyName(recruitmentEntity.getCompanyEntity().getName())
                .areas(RecruitAreaResponse.of(recruitAreas))
                .pay(recruitmentEntity.getPayInfo().getPay())
                .trainPay(recruitmentEntity.getPayInfo().getTrainPay())
                .etc(recruitmentEntity.getEtc())
                .submitDocument(recruitmentEntity.getSubmitDocument())
                .requiredGrade(recruitmentEntity.getRequiredGrade())
                .requiredLicenses(recruitmentEntity.getRequiredLicenses())
                .startDate(recruitmentEntity.getRecruitDate().getStartDate())
                .endDate(recruitmentEntity.getRecruitDate().getFinishDate())
                .hiringProgress(recruitmentEntity.getHiringProgress())
                .workHours(recruitmentEntity.getWorkingHours())
                .preferentialTreatment(recruitmentEntity.getPreferentialTreatment())
                .military(recruitmentEntity.getMilitarySupport())
                .benefits(recruitmentEntity.getBenefits())
                .build();
    }
}
