package team.retum.jobis.thirdparty.webhook;

import team.retum.jobis.domain.bug.model.BugAttachment;
import team.retum.jobis.domain.bug.model.BugReport;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface WebhookUtil {

    void sendBugReport(BugReport bugReport, List<BugAttachment> bugAttachments, String writer);

    void sendExceptionInfo(HttpServletRequest request, Exception exception);
}
