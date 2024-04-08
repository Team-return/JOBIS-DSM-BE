package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.WriteFilePort;
import team.retum.jobis.domain.recruitment.dto.RecruitmentFilter;
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

        RecruitmentFilter filter = RecruitmentFilter.builder()
                .page(1L)
                .build();

        List<TeacherRecruitmentVO> recruitmentList = queryRecruitmentPort.queryTeacherRecruitmentsByFilter(filter);

        return new ExportRecruitmentHistoryResponse(
                writeFilePort.writeRecruitmentExcelFile(recruitmentList),
                getFileName()
        );
    }

    private String getFileName() {
        return "모집의뢰서 엑셀 출력";
    }
}
