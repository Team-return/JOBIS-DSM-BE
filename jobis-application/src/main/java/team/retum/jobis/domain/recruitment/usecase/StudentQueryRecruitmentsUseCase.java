package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.dto.response.TotalPageCountResponse;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.common.util.NumberUtil;
import team.retum.jobis.domain.recruitment.dto.StudentRecruitmentFilter;
import team.retum.jobis.domain.recruitment.dto.response.StudentQueryRecruitmentsResponse;
import team.retum.jobis.domain.recruitment.dto.response.StudentQueryRecruitmentsResponse.StudentRecruitmentResponse;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

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
        Boolean militarySupport,
        List<Integer> years,
        RecruitStatus status
    ) {
        Long currentStudentId = securityPort.getCurrentUserId();
        StudentRecruitmentFilter studentRecruitmentFilter = StudentRecruitmentFilter.builder()
            .years(years)
            .companyName(name)
            .page(page)
            .limit(12)
            .jobCode(jobCode)
            .techCodes(techCodesIds)
            .studentId(currentStudentId)
            .winterIntern(winterIntern)
            .militarySupport(militarySupport)
            .status(status)
            .build();

        List<StudentRecruitmentResponse> recruitments =
            queryRecruitmentPort.getStudentRecruitmentsBy(studentRecruitmentFilter).stream()
                .map(StudentRecruitmentResponse::from)
                .toList();

        return new StudentQueryRecruitmentsResponse(recruitments);
    }

    public TotalPageCountResponse getTotalPageCount(
        String name,
        Long jobCode,
        List<Long> techCodesIds,
        Boolean winterIntern,
        Boolean militarySupport,
        List<Integer> years,
        RecruitStatus status
    ) {
        Long currentStudentId = securityPort.getCurrentUserId();

        StudentRecruitmentFilter filter = StudentRecruitmentFilter.builder()
            .years(years)
            .companyName(name)
            .jobCode(jobCode)
            .techCodes(techCodesIds)
            .studentId(currentStudentId)
            .winterIntern(winterIntern)
            .militarySupport(militarySupport)
            .status(status)
            .limit(12)
            .build();

        int totalPageCount = NumberUtil.getTotalPageCount(
            queryRecruitmentPort.getCountByStudentFilter(filter), filter.getLimit()
        );

        return new TotalPageCountResponse(totalPageCount);
    }
}
