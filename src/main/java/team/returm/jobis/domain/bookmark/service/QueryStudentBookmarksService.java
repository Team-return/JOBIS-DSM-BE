package team.returm.jobis.domain.bookmark.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.bookmark.domain.repository.BookmarkRepository;
import team.returm.jobis.domain.bookmark.domain.repository.vo.QueryStudentBookmarksVO;
import team.returm.jobis.domain.bookmark.presentation.dto.response.QueryStudentBookmarksResponse;
import team.returm.jobis.domain.student.domain.Student;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.Service;

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
