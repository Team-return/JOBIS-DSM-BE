package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.dto.response.TotalPageCountResponse;
import team.retum.jobis.common.util.NumberUtil;
import team.retum.jobis.common.util.StringUtil;
import team.retum.jobis.domain.code.model.Code;
import team.retum.jobis.domain.code.spi.QueryCodePort;
import team.retum.jobis.domain.recruitment.dto.RecruitmentFilter;
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
    private final QueryCodePort queryCodePort;

    public TeacherQueryRecruitmentsResponse execute(String companyName, LocalDate start, LocalDate end,
                                                    Integer year, RecruitStatus status, Long page) {
        RecruitmentFilter filter = RecruitmentFilter.builder()
                .companyName(companyName)
                .status(status)
                .startDate(start)
                .endDate(end)
                .codes(List.of())
                .year(year)
                .page(page)
                .build();

        List<TeacherRecruitmentResponse> recruitments =
                queryRecruitmentPort.queryRecruitmentsByFilter(filter).stream()
                        .map(recruitment ->
                                TeacherRecruitmentResponse.builder()
                                        .id(recruitment.getRecruitmentId())
                                        .recruitmentStatus(recruitment.getRecruitStatus())
                                        .companyName(recruitment.getCompanyName())
                                        .companyType(recruitment.getCompanyType())
                                        .start(recruitment.getStartDate())
                                        .end(recruitment.getEndDate())
                                        .militarySupport(recruitment.isMilitarySupport())
                                        .applicationRequestedCount(recruitment.getRequestedApplicationCount())
                                        .applicationApprovedCount(recruitment.getApprovedApplicationCount())
                                        .recruitmentCount(recruitment.getTotalHiring())
                                        .recruitmentJob(getJobKeywords(recruitment.getJobCodes()))
                                        .build()
                        ).toList();

        return new TeacherQueryRecruitmentsResponse(recruitments);
    }

    public TotalPageCountResponse getTotalPageCount(String companyName, LocalDate start, LocalDate end,
                                                    Integer year, RecruitStatus status) {
        RecruitmentFilter filter = RecruitmentFilter.builder()
                .companyName(companyName)
                .status(status)
                .startDate(start)
                .endDate(end)
                .codes(List.of())
                .year(year)
                .build();

        int totalPageCount = NumberUtil.getTotalPageCount(
                queryRecruitmentPort.getRecruitmentCountByFilter(filter), filter.getLimit()
        );

        return new TotalPageCountResponse(totalPageCount);
    }

    private String getJobKeywords(String jobCodes) {
        return StringUtil.joinStringList(
                queryCodePort.queryCodesByIdIn(
                        StringUtil.divideString(jobCodes, ",").stream().map(Long::parseLong).toList()
                ).stream().map(Code::getKeyword).toList(),
                "/"
        );
    }
}
