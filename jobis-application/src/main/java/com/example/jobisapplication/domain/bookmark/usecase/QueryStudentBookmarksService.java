package com.example.jobisapplication.domain.bookmark.usecase;

import com.example.jobisapplication.common.annotation.ReadOnlyUseCase;
import com.example.jobisapplication.common.spi.SecurityPort;
import com.example.jobisapplication.domain.bookmark.spi.QueryBookmarkPort;
import com.example.jobisapplication.domain.bookmark.spi.vo.StudentBookmarksVO;
import com.example.jobisapplication.domain.student.model.Student;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.bookmark.dto.response.QueryStudentBookmarksResponse;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryStudentBookmarksService {

    private final QueryBookmarkPort queryBookmarkPort;
    private final SecurityPort securityPort;

    public QueryStudentBookmarksResponse execute() {
        Long currentUserId = securityPort.getCurrentUserId();

        List<StudentBookmarksVO> bookmarks = queryBookmarkPort.queryBookmarksByStudentId(currentUserId);

        return new QueryStudentBookmarksResponse(bookmarks);
    }
}
