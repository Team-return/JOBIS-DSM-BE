package team.retum.jobis.domain.notification.spi;

import team.retum.jobis.domain.notice.spi.vo.TopicVO;

import java.util.List;

public interface QueryTopicSubscriptionPort {

    List<TopicVO> getAllByDeviceToken(String deviceToken);

}
