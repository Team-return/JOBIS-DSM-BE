package team.retum.jobis.domain.company.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.company.spi.vo.StudentCompaniesVO;

import java.util.List;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class StudentQueryCompaniesResponse {

    private final List<StudentCompaniesVO> companies;
}
