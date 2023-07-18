package com.example.jobisapplication.domain.bug.dto;

import com.example.jobisapplication.domain.bug.spi.vo.BugReportsVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryBugReportsResponse {

    private final List<BugReportsVO> bugReports;
}
