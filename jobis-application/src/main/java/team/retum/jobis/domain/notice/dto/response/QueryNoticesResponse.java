package team.retum.jobis.domain.notice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.notice.spi.vo.NoticeVO;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryNoticesResponse {

    private final List<NoticeVO> notices;

}
