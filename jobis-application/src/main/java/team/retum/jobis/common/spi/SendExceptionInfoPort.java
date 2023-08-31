package team.retum.jobis.common.spi;

import javax.servlet.http.HttpServletRequest;

public interface SendExceptionInfoPort {

    void sendExceptionInfo(HttpServletRequest request, Exception e);
}
