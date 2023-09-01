package team.retum.jobis.domain.bug.spi;

import team.retum.jobis.domain.bug.model.BugAttachment;
import team.retum.jobis.domain.bug.model.BugReport;

import java.util.List;

public interface PublishBugReportEventPort {

    void publishBugReport(BugReport bugReport, List<BugAttachment> bugAttachments, String writer);
}
