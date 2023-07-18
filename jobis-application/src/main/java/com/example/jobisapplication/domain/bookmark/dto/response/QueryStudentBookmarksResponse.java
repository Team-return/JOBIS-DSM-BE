package com.example.jobisapplication.domain.bookmark.dto.response;

import com.example.jobisapplication.domain.bookmark.spi.vo.StudentBookmarksVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryStudentBookmarksResponse {

    private final List<StudentBookmarksVO> bookmarks;
}
