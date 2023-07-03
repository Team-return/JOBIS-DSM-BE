package team.retum.jobis.domain.bookmark.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.bookmark.domain.repository.BookmarkRepository;
import team.retum.jobis.domain.bookmark.domain.repository.vo.QueryStudentBookmarksVO;
import team.retum.jobis.domain.bookmark.presentation.dto.response.QueryStudentBookmarksResponse;
import team.retum.jobis.domain.student.domain.Student;
import team.retum.jobis.domain.user.facade.UserFacade;
import team.retum.jobis.global.annotation.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryStudentBookmarksService {

    private final BookmarkRepository bookmarkRepository;
    private final UserFacade userFacade;

    public QueryStudentBookmarksResponse execute() {
        Student student = userFacade.getCurrentStudent();
        List<QueryStudentBookmarksVO> bookmarks = bookmarkRepository.queryBookmarksByStudentId(student.getId());

        return new QueryStudentBookmarksResponse(bookmarks);
    }
}