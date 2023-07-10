package team.retum.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.code.domain.Code;
import team.retum.jobis.domain.code.facade.CodeFacade;
import team.retum.jobis.domain.recruitment.domain.enums.RecruitStatus;
import team.retum.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.retum.jobis.domain.recruitment.presentation.dto.RecruitmentFilter;
import team.retum.jobis.domain.recruitment.presentation.dto.response.StudentQueryRecruitmentsResponse;
import team.retum.jobis.domain.recruitment.presentation.dto.response.StudentQueryRecruitmentsResponse.StudentRecruitmentResponse;
import team.retum.jobis.domain.user.facade.UserFacade;
import team.retum.jobis.global.annotation.ReadOnlyService;

import java.time.Year;
import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class StudentQueryRecruitmentsService {

    private final RecruitmentRepository recruitmentRepository;
    private final UserFacade userFacade;
    private final CodeFacade codeFacade;

    public StudentQueryRecruitmentsResponse execute(
            String name,
            Integer page,
            Long jobCode,
            List<Long> codeIds
    ) {
        Long studentId = userFacade.getCurrentUserId();

        String jobKeyword = validJobCode(jobCode);
        List<Code> techCodes = codeFacade.queryCodesByIdIn(codeIds);

        RecruitmentFilter recruitmentFilter = RecruitmentFilter.builder()
                .year(Year.now().getValue())
                .status(RecruitStatus.RECRUITING)
                .companyName(name)
                .page(page)
                .codes(techCodes)
                .studentId(studentId)
                .jobKeyword(jobKeyword)
                .build();

        List<StudentRecruitmentResponse> recruitments =
                recruitmentRepository.queryRecruitmentsByConditions(recruitmentFilter).stream()
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
            return codeFacade.findCodeById(jobCode).getKeyword();
        } else {
            return null;
        }
    }
}
