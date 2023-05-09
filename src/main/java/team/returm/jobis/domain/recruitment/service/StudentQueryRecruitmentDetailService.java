package team.returm.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.returm.jobis.domain.recruitment.domain.repository.vo.RecruitAreaVO;
import team.returm.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.returm.jobis.domain.recruitment.presentation.dto.response.RecruitAreaResponse;
import team.returm.jobis.domain.recruitment.presentation.dto.response.StudentRecruitDetailsResponse;
import team.returm.jobis.global.annotation.ReadOnlyService;
import team.returm.jobis.global.util.StringUtil;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class StudentQueryRecruitmentDetailService {
    private final RecruitmentRepository recruitmentRepository;

    public StudentRecruitDetailsResponse execute(Long recruitId) {
        Recruitment recruitment = recruitmentRepository.queryRecruitmentById(recruitId)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        List<RecruitAreaVO> recruitAreas =
                recruitmentRepository.queryRecruitAreasByRecruitmentId(recruitment.getId());

        return StudentRecruitDetailsResponse.builder()
                .companyId(recruitment.getCompany().getId())
                .areas(RecruitAreaResponse.of(recruitAreas))
                .pay(recruitment.getPayInfo().getPay())
                .trainPay(recruitment.getPayInfo().getTrainingPay())
                .etc(recruitment.getEtc())
                .requiredGrade(recruitment.getRequiredGrade())
                .requiredLicenses(StringUtil.divideString(recruitment.getRequiredLicenses()))
                .startDate(recruitment.getRecruitDate().getStartDate())
                .endDate(recruitment.getRecruitDate().getFinishDate())
                .hiringProgress(StringUtil.divideString(recruitment.getHiringProgress()))
                .workHours(recruitment.getWorkingHours())
                .preferentialTreatment(recruitment.getPreferentialTreatment())
                .military(recruitment.getMilitarySupport())
                .benefits(recruitment.getBenefits())
                .build();
    }
}
