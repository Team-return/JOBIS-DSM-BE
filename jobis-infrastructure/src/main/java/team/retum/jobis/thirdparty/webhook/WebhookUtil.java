package team.retum.jobis.thirdparty.webhook;

import team.retum.jobis.domain.bug.model.BugReport;

import jakarta.servlet.http.HttpServletRequest;

public interface WebhookUtil {

    void sendBugReport(BugReport bugReport, String writer);

    void sendExceptionInfo(HttpServletRequest request, Exception exception);
}
