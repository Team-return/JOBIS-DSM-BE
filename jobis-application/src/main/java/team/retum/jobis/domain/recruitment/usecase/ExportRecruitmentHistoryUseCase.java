package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.WriteFilePort;
import team.retum.jobis.domain.recruitment.dto.RecruitmentFilter;
import team.retum.jobis.domain.recruitment.dto.response.ExportRecruitmentHistoryResponse;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;
import team.retum.jobis.domain.recruitment.spi.vo.TeacherRecruitmentVO;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class ExportRecruitmentHistoryUseCase {

    private final WriteFilePort writeFilePort;
    private final QueryRecruitmentPort queryRecruitmentPort;

    public ExportRecruitmentHistoryResponse execute() {
        int year = LocalDate.now().getYear();

        RecruitmentFilter filter = RecruitmentFilter.builder()
            .year(year)
            .codes(List.of())
            .build();

        List<TeacherRecruitmentVO> recruitmentList =
            queryRecruitmentPort.getTeacherRecruitmentsWithoutPageBy(filter);

        return new ExportRecruitmentHistoryResponse(
            writeFilePort.writeRecruitmentExcelFile(recruitmentList),
            getFileName(year)
        );
    }

    private String getFileName(Integer year) {
        return year + "_모집의뢰서_엑셀_출력";
    }
}
