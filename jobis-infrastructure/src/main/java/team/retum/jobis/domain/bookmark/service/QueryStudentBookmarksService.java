package team.retum.jobis.domain.bookmark.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.bookmark.persistence.repository.BookmarkRepository;
import team.retum.jobis.domain.bookmark.persistence.repository.vo.QueryStudentBookmarksVO;
import com.example.jobisapplication.domain.bookmark.dto.response.QueryStudentBookmarksResponse;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.user.facade.UserFacade;
import com.example.jobisapplication.common.annotation.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryStudentBookmarksService {

    private final BookmarkRepository bookmarkRepository;
    private final UserFacade userFacade;

    public QueryStudentBookmarksResponse execute() {
        StudentEntity studentEntity = userFacade.getCurrentStudent();
        List<QueryStudentBookmarksVO> bookmarks = bookmarkRepository.queryBookmarksByStudentId(studentEntity.getId());

        return new QueryStudentBookmarksResponse(bookmarks);
    }
}
