package team.retum.jobis.domain.company.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.company.domain.repository.vo.TeacherQueryEmployCompaniesVO;

import java.util.List;

@Getter
@AllArgsConstructor
public class TeacherQueryEmployCompaniesResponse {

    private final List<TeacherQueryEmployCompaniesVO> companies;
}
