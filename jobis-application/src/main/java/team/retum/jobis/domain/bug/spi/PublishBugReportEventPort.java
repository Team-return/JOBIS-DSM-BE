package team.retum.jobis.domain.bug.spi;

import team.retum.jobis.domain.bug.model.BugReport;

public interface PublishBugReportEventPort {

    void publishBugReport(BugReport bugReport, String writer);
}
