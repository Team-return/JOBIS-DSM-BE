package team.retum.jobis.domain.company.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.company.spi.vo.RecentCompanyVO;

import java.util.List;

@Getter
@AllArgsConstructor
public class StudentQueryRecentCompaniesResponse {

    private final List<RecentCompanyVO> companies;
}
