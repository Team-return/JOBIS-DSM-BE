package com.example.jobisapplication.domain.bug.spi;

import com.example.jobisapplication.domain.bug.model.BugReport;
import com.example.jobisapplication.domain.bug.model.DevelopmentArea;
import com.example.jobisapplication.domain.bug.spi.vo.BugReportsVO;

import java.util.List;

public interface QueryBugReportPort {

    BugReport queryBugReportById(Long id);

    List<BugReportsVO> queryBugReportsByDevelopmentArea(DevelopmentArea developmentArea);
}
