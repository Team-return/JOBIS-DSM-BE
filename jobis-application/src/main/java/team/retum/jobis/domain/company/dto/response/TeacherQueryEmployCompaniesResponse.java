package team.retum.jobis.domain.company.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.company.spi.vo.TeacherEmployCompaniesVO;

import java.util.List;

@Getter
@AllArgsConstructor
public class TeacherQueryEmployCompaniesResponse {

    private final List<TeacherEmployCompaniesVO> companies;
    private final int totalPageCount;
}
