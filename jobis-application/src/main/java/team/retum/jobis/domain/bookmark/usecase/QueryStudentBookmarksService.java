package team.retum.jobis.domain.bookmark.usecase;

import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.bookmark.spi.QueryBookmarkPort;
import team.retum.jobis.domain.bookmark.spi.vo.StudentBookmarksVO;
import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.bookmark.dto.response.QueryStudentBookmarksResponse;

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
