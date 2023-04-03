package team.returm.jobis.domain.recruitment.service;

import java.time.Year;
import java.util.List;
import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.code.domain.RecruitAreaCode;
import team.returm.jobis.domain.code.domain.enums.CodeType;
import team.returm.jobis.domain.code.domain.repository.CodeRepository;
import team.returm.jobis.domain.recruitment.presentation.dto.response.StudentQueryRecruitmentsResponse;
import team.returm.jobis.domain.recruitment.presentation.dto.response.StudentQueryRecruitmentsResponse.StudentRecruitmentResponse;
import java.time.Year;
import java.util.List;
import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.recruitment.domain.enums.RecruitStatus;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.returm.jobis.global.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class StudentQueryRecruitmentsService {

    private final RecruitmentRepository recruitmentRepository;
    private final CodeRepository codeRepository;

    public StudentQueryRecruitmentsResponse execute(String name, Integer page, List<String> keywords) {

        List<RecruitAreaCode> codes = codeRepository.queryCodesByKeywordsAndCodeType(keywords, CodeType.TECH);

        List<StudentRecruitmentResponse> recruitments =
                recruitmentRepository.queryRecruitmentsByConditions(
                            Year.now().getValue(), null, null,
                                RecruitStatus.RECRUITING, name, page-1, codes
                        ).stream()
                        .map(
                                r -> StudentRecruitmentResponse.builder()
                                        .recruitId(r.getRecruitment().getId())
                                        .companyName(r.getCompany().getName())
                                        .trainPay(r.getRecruitment().getPayInfo().getTrainingPay())
                                        .jobCodeList(r.getRecruitAreaList())
                                        .military(r.getRecruitment().getMilitarySupport())
                                        .companyProfileUrl(r.getCompany().getCompanyLogoUrl())
                                        .totalHiring(r.getTotalHiring())
                                        .build()
                        ).toList();

        return new StudentQueryRecruitmentsResponse(recruitments);
    }

}
