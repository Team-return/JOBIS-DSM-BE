package team.retum.jobis.domain.bug.spi;

import team.retum.jobis.domain.bug.model.BugAttachment;
import team.retum.jobis.domain.bug.model.BugReport;

import java.util.List;

public interface CommandBugReportPort {

    BugReport saveBugReport(BugReport bugReport);
}
