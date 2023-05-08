package team.returm.jobis.domain.company.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.returm.jobis.domain.company.domain.repository.vo.StudentQueryCompaniesVO;

import java.util.List;

@Getter
@AllArgsConstructor
public class StudentQueryCompaniesResponse {
    private final List<StudentQueryCompaniesVO> companies;
}
