package team.retum.jobis.common.spi;

import javax.servlet.http.HttpServletRequest;

public interface PublishExceptionPort {

    void publishException(HttpServletRequest request, Exception e);
}
