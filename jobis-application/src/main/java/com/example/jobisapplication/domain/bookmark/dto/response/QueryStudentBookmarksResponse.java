package com.example.jobisapplication.domain.bookmark.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.bookmark.persistence.repository.vo.QueryStudentBookmarksVO;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryStudentBookmarksResponse {

    private final List<QueryStudentBookmarksVO> bookmarks;
}
