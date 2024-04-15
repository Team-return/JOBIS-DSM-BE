package team.retum.jobis.domain.notice.spi;

import team.retum.jobis.domain.notice.model.Notice;

public interface CommandNoticePort {

    Notice saveNotice(Notice notice);

    void deleteNotice(Notice notice);

}
