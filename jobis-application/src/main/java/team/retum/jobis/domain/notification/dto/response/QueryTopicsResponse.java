package team.retum.jobis.domain.notification.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.notice.spi.vo.TopicVO;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryTopicsResponse {

    private final List<TopicVO> topics;

}
