package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.common.util.NumberUtil;
import team.retum.jobis.domain.code.exception.CodeNotFoundException;
import team.retum.jobis.domain.code.spi.QueryCodePort;
import team.retum.jobis.domain.recruitment.dto.RecruitmentFilter;
import team.retum.jobis.domain.recruitment.dto.response.StudentQueryRecruitmentCountResponse;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

import java.time.Year;
import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class StudentQueryRecruitmentCountUseCase {

    private final QueryRecruitmentPort queryRecruitmentPort;
    private final SecurityPort securityPort;
    private final QueryCodePort queryCodePort;

    public StudentQueryRecruitmentCountResponse execute(String name, Long page, Long jobCode, List<Long> codeIds) {
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

        return new StudentQueryRecruitmentCountResponse(totalPageCount);
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
