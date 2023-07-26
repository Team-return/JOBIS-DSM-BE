package com.example.jobisapplication.domain.recruitment.usecase;

import com.example.jobisapplication.common.annotation.ReadOnlyUseCase;
import com.example.jobisapplication.common.spi.SecurityPort;
import com.example.jobisapplication.domain.code.model.Code;
import com.example.jobisapplication.domain.code.spi.QueryCodePort;
import com.example.jobisapplication.domain.recruitment.dto.RecruitmentFilter;
import com.example.jobisapplication.domain.recruitment.dto.response.StudentQueryRecruitmentsResponse;
import com.example.jobisapplication.domain.recruitment.dto.response.StudentQueryRecruitmentsResponse.StudentRecruitmentResponse;
import com.example.jobisapplication.domain.recruitment.model.RecruitStatus;
import com.example.jobisapplication.domain.recruitment.spi.QueryRecruitmentPort;
import lombok.RequiredArgsConstructor;

import java.time.Year;
import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class StudentQueryRecruitmentsUseCase {

    private final QueryRecruitmentPort queryRecruitmentPort;
    private final SecurityPort securityPort;
    private final QueryCodePort queryCodePort;

    public StudentQueryRecruitmentsResponse execute(
            String name,
            Integer page,
            Long jobCode,
            List<Long> codeIds
    ) {
        Long currentStudentId = securityPort.getCurrentUserId();

        String jobKeyword = validJobCode(jobCode);
        List<Code> techCodes = queryCodePort.queryCodesByIdIn(codeIds);

        RecruitmentFilter recruitmentFilter = RecruitmentFilter.builder()
                .year(Year.now().getValue())
                .status(RecruitStatus.RECRUITING)
                .companyName(name)
                .page(page)
                .codeEntities(techCodes)
                .studentId(currentStudentId)
                .jobKeyword(jobKeyword)
                .build();

        List<StudentRecruitmentResponse> recruitments =
                queryRecruitmentPort.queryRecruitmentsByFilter(recruitmentFilter).stream()
                        .map(
                                recruitment -> StudentRecruitmentResponse.builder()
                                        .recruitId(recruitment.getRecruitmentId())
                                        .companyName(recruitment.getCompanyName())
                                        .trainPay(recruitment.getTrainPay())
                                        .jobCodeList(recruitment.getRecruitAreaList())
                                        .military(recruitment.isMilitarySupport())
                                        .companyProfileUrl(recruitment.getCompanyLogoUrl())
                                        .totalHiring(recruitment.getTotalHiring())
                                        .isBookmarked(recruitment.getIsBookmarked() != 0)
                                        .build()
                        ).toList();

        return new StudentQueryRecruitmentsResponse(recruitments);
    }

    private String validJobCode(Long jobCode) {
        if (jobCode != null) {
            return queryCodePort.queryCodeById(jobCode).getKeyword();
        } else {
            return null;
        }
    }
}
