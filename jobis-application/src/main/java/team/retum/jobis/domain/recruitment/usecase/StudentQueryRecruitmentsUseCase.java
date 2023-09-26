package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.dto.response.TotalPageCountResponse;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.common.util.NumberUtil;
import team.retum.jobis.domain.code.exception.CodeNotFoundException;
import team.retum.jobis.domain.code.spi.QueryCodePort;
import team.retum.jobis.domain.recruitment.dto.RecruitmentFilter;
import team.retum.jobis.domain.recruitment.dto.response.StudentQueryRecruitmentsResponse;
import team.retum.jobis.domain.recruitment.dto.response.StudentQueryRecruitmentsResponse.StudentRecruitmentResponse;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

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
            Long page,
            Long jobCode,
            List<Long> codeIds
    ) {
        Long currentStudentId = securityPort.getCurrentUserId();
        String jobKeyword = validJobCode(jobCode);

        RecruitmentFilter recruitmentFilter = RecruitmentFilter.builder()
                .year(Year.now().getValue())
                .status(RecruitStatus.RECRUITING)
                .companyName(name)
                .page(page)
                .limit(12)
                .codes(codeIds)
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

    public TotalPageCountResponse getTotalPageCount(String name, Long page, Long jobCode, List<Long> codeIds) {
        Long currentStudentId = securityPort.getCurrentUserId();
        String jobKeyword = validJobCode(jobCode);

        RecruitmentFilter filter = RecruitmentFilter.builder()
                .year(Year.now().getValue())
                .status(RecruitStatus.RECRUITING)
                .companyName(name)
                .page(page)
                .limit(12)
                .codes(codeIds)
                .studentId(currentStudentId)
                .jobKeyword(jobKeyword)
                .build();

        int totalPageCount = NumberUtil.getTotalPageCount(
                queryRecruitmentPort.getRecruitmentCountByFilter(filter), filter.getLimit()
        );

        return new TotalPageCountResponse(totalPageCount);
    }

    private String validJobCode(Long jobCode) {
        if (jobCode != null) {
            return queryCodePort.queryCodeById(jobCode)
                    .orElseThrow(() -> CodeNotFoundException.EXCEPTION)
                    .getKeyword();
        } else {
            return null;
        }
    }
}
