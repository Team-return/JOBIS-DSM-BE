package team.returm.jobis.domain.company.presentation.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import team.returm.jobis.domain.company.domain.repository.vo.StudentQueryCompaniesVO;

@Getter
@AllArgsConstructor
public class StudentQueryCompaniesResponse {
    private final List<StudentQueryCompaniesVO> companies;
}
