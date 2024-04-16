package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.WriteFilePort;
import team.retum.jobis.domain.recruitment.dto.response.ExportRecruitmentHistoryResponse;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;
import team.retum.jobis.domain.recruitment.spi.vo.TeacherRecruitmentVO;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class ExportRecruitmentHistoryUseCase {
    private final WriteFilePort writeFilePort;
    private final QueryRecruitmentPort queryRecruitmentPort;

    public ExportRecruitmentHistoryResponse execute() {
        LocalDate now = LocalDate.now();

        List<TeacherRecruitmentVO> recruitmentList = queryRecruitmentPort.queryTeacherRecruitmentsByYear(now.getYear());

        return new ExportRecruitmentHistoryResponse(
            writeFilePort.writeRecruitmentExcelFile(recruitmentList),
            getFileName(now.getYear())
        );
    }

    private String getFileName(Integer year) {
        return year + "_모집의뢰서_엑셀_출력";
    }
}
