package com.example.jobisapplication.domain.bug.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.bug.persistence.repository.vo.QueryBugReportsVO;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryBugReportsResponse {

    private final List<QueryBugReportsVO> bugReports;
}
