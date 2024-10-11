package team.retum.jobis.domain.notice.spi;

import team.retum.jobis.domain.notice.model.Notice;

public interface CommandNoticePort {

    Notice save(Notice notice);

    void delete(Notice notice);

}
