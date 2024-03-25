package team.retum.jobis.domain.recruitment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.recruitment.spi.vo.MyAllRecruitmentsVO;

import java.util.List;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class QueryMyRecruitmentsResponse {

    private final List<MyAllRecruitmentsVO> myRecruitments;
}
