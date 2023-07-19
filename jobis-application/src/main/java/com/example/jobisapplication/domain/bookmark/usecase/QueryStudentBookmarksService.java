package com.example.jobisapplication.domain.bookmark.usecase;

import com.example.jobisapplication.common.annotation.ReadOnlyUseCase;
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
    private final UserFacade userFacade;

    public QueryStudentBookmarksResponse execute() {
        Student studentEntity = userFacade.getCurrentStudent();
        List<StudentBookmarksVO> bookmarks = queryBookmarkPort.queryBookmarksByStudentId(studentEntity.getId());

        return new QueryStudentBookmarksResponse(bookmarks);
    }
}
