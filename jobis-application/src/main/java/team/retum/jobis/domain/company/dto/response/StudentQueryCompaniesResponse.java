package team.retum.jobis.domain.company.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.company.spi.vo.StudentCompaniesVO;

import java.util.List;

@Getter
@AllArgsConstructor
public class StudentQueryCompaniesResponse {
    private final List<StudentCompaniesVO> companies;
}
