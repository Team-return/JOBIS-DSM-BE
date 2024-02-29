package team.retum.jobis.domain.notice.spi;

import team.retum.jobis.domain.notice.model.Notice;

import java.util.Optional;

public interface QueryNoticePort {

    Optional<Notice> queryNoticeById(Long noticeId);
}
