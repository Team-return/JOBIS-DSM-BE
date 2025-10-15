package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.dto.response.TotalPageCountResponse;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.common.util.NumberUtil;
import team.retum.jobis.domain.recruitment.dto.RecruitmentFilter;
import team.retum.jobis.domain.recruitment.dto.StudentRecruitmentFilter;
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

    public StudentQueryRecruitmentsResponse execute(
        String name,
        Long page,
        Long jobCode,
        List<Long> techCodesIds,
        Boolean winterIntern,
        Boolean militarySupport
    ) {
        Long currentStudentId = securityPort.getCurrentUserId();
        StudentRecruitmentFilter studentRecruitmentFilter = StudentRecruitmentFilter.builder()
            .year(Year.now().getValue())
            .status(RecruitStatus.RECRUITING)
            .companyName(name)
            .page(page)
            .limit(12)
            .jobCode(jobCode)
            .techCodes(techCodesIds)
            .studentId(currentStudentId)
            .winterIntern(winterIntern)
            .militarySupport(militarySupport)
            .build();

        List<StudentRecruitmentResponse> recruitments =
            queryRecruitmentPort.getStudentRecruitmentsBy(studentRecruitmentFilter).stream()
                .map(StudentRecruitmentResponse::from)
                .toList();

        return new StudentQueryRecruitmentsResponse(recruitments);
    }

    public TotalPageCountResponse getTotalPageCount(String name, List<Long> codeIds, Boolean winterIntern) {
        Long currentStudentId = securityPort.getCurrentUserId();

        RecruitmentFilter filter = RecruitmentFilter.builder()
            .year(Year.now().getValue())
            .status(RecruitStatus.RECRUITING)
            .companyName(name)
            .limit(12)
            .codes(codeIds)
            .studentId(currentStudentId)
            .winterIntern(winterIntern)
            .build();

        int totalPageCount = NumberUtil.getTotalPageCount(
            queryRecruitmentPort.getCountBy(filter), filter.getLimit()
        );

        return new TotalPageCountResponse(totalPageCount);
    }
}
