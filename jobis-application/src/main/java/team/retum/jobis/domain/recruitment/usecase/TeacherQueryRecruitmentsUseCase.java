package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.dto.response.TotalPageCountResponse;
import team.retum.jobis.common.util.NumberUtil;
import team.retum.jobis.domain.recruitment.dto.RecruitmentFilter;
import team.retum.jobis.domain.recruitment.dto.response.RecruitmentCountResponse;
import team.retum.jobis.domain.recruitment.dto.response.TeacherQueryManualRecruitmentsResponse;
import team.retum.jobis.domain.recruitment.dto.response.TeacherQueryManualRecruitmentsResponse.TeacherManualRecruitmentResponse;
import team.retum.jobis.domain.recruitment.dto.response.TeacherQueryRecruitmentsResponse;
import team.retum.jobis.domain.recruitment.dto.response.TeacherQueryRecruitmentsResponse.TeacherRecruitmentResponse;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class TeacherQueryRecruitmentsUseCase {

    private final QueryRecruitmentPort queryRecruitmentPort;

    public TeacherQueryRecruitmentsResponse execute(String companyName, LocalDate start, LocalDate end, Integer year,
                                                    RecruitStatus status, Long page, Boolean winterIntern, Boolean militarySupport, List<Long> codeIds) {
        RecruitmentFilter filter = RecruitmentFilter.builder()
            .companyName(companyName)
            .status(status)
            .startDate(start)
            .endDate(end)
            .codes(List.of())
            .year(year)
            .page(page)
            .winterIntern(winterIntern)
            .militarySupport(militarySupport)
            .codes(codeIds)
            .build();

        List<TeacherRecruitmentResponse> recruitments =
            queryRecruitmentPort.getTeacherRecruitmentsBy(filter).stream()
                .map(TeacherRecruitmentResponse::from)
                .toList();

        return new TeacherQueryRecruitmentsResponse(recruitments);
    }

    public TeacherQueryRecruitmentsResponse executeWithoutPage(int year, List<Long> codeIds, Boolean winterIntern, Boolean militarySupport) {
        RecruitmentFilter filter = RecruitmentFilter.builder()
            .codes(codeIds)
            .year(year)
            .winterIntern(winterIntern)
            .militarySupport(militarySupport)
            .build();

        List<TeacherRecruitmentResponse> recruitments =
            queryRecruitmentPort.getTeacherRecruitmentsWithoutPageBy(filter).stream()
                .map(TeacherRecruitmentResponse::from)
                .toList();

        return new TeacherQueryRecruitmentsResponse(recruitments);
    }

    public RecruitmentCountResponse countRecruitments(String companyName, LocalDate start, LocalDate end, Integer year,
                                                      RecruitStatus status, Boolean winterIntern, Boolean militarySupport, List<Long> codeIds) {
        RecruitmentFilter filter = RecruitmentFilter.builder()
            .companyName(companyName)
            .status(status)
            .startDate(start)
            .endDate(end)
            .codes(List.of())
            .year(year)
            .winterIntern(winterIntern)
            .militarySupport(militarySupport)
            .codes(codeIds)
            .page(1L)
            .limit(1000)
            .build();

        List<TeacherRecruitmentResponse> recruitments =
            queryRecruitmentPort.getTeacherRecruitmentsBy(filter).stream()
                .map(TeacherRecruitmentResponse::from)
                .toList();

        return new RecruitmentCountResponse(recruitments.size());
    }

    public TotalPageCountResponse getTotalPageCount(String companyName, LocalDate start, LocalDate end,
                                                    Integer year, RecruitStatus status, Boolean winterIntern) {
        RecruitmentFilter filter = RecruitmentFilter.builder()
            .companyName(companyName)
            .status(status)
            .startDate(start)
            .endDate(end)
            .codes(List.of())
            .year(year)
            .winterIntern(winterIntern)
            .build();

        int totalPageCount = NumberUtil.getTotalPageCount(
            queryRecruitmentPort.getCountBy(filter), filter.getLimit()
        );

        return new TotalPageCountResponse(totalPageCount);
    }

    public TeacherQueryManualRecruitmentsResponse executeRegisteredByTeacher() {
        List<TeacherManualRecruitmentResponse> recruitments =
            queryRecruitmentPort.getTeacherManualRecruitments().stream()
                .map(TeacherManualRecruitmentResponse::from)
                .toList();

        return new TeacherQueryManualRecruitmentsResponse(recruitments);
    }
}
