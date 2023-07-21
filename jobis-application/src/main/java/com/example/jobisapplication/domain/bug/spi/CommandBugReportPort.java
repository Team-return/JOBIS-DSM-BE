package com.example.jobisapplication.domain.bug.spi;

import com.example.jobisapplication.domain.bug.model.BugAttachment;
import com.example.jobisapplication.domain.bug.model.BugReport;

import java.util.List;

public interface CommandBugReportPort {

    BugReport saveBugReport(BugReport bugReport);

    List<BugAttachment> saveAllBugAttachment(List<BugAttachment> bugAttachments);
}
