package team.retum.jobis.domain.bug.spi;

import team.retum.jobis.domain.bug.model.BugReport;

public interface CommandBugReportPort {

    BugReport save(BugReport bugReport);
}
