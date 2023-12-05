package team.retum.jobis.thirdparty.webhook;

import jakarta.servlet.http.HttpServletRequest;
import team.retum.jobis.domain.bug.model.BugReport;

public interface WebhookUtil {

    void sendBugReport(BugReport bugReport, String writer);

    void sendExceptionInfo(HttpServletRequest request, Exception exception);
}
