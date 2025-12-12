package team.retum.jobis.domain.notice.spi;

import team.retum.jobis.domain.notice.model.ViewLog;

public interface CommandViewLogPort {

    ViewLog save(ViewLog viewLog);

}
