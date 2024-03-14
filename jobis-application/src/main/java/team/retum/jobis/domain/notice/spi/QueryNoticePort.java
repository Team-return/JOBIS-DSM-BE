package team.retum.jobis.domain.notice.spi;

import team.retum.jobis.domain.notice.model.Notice;
import team.retum.jobis.domain.notice.spi.vo.NoticeVO;

import java.util.List;
import java.util.Optional;

public interface QueryNoticePort {

    Optional<Notice> queryNoticeById(Long noticeId);

    List<NoticeVO> queryNotices();

}
