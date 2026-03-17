package team.retum.jobis.domain.bookmark.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.bookmark.dto.response.QueryStudentBookmarksResponse;
import team.retum.jobis.domain.bookmark.dto.response.QueryStudentBookmarksResponse.QueryStudentBookmarkResponse;
import team.retum.jobis.domain.bookmark.spi.QueryBookmarkPort;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryStudentBookmarksUseCase {

    private final QueryBookmarkPort queryBookmarkPort;
    private final SecurityPort securityPort;

    public QueryStudentBookmarksResponse execute() {
        Long currentUserId = securityPort.getCurrentUserId();

        List<QueryStudentBookmarkResponse> bookmarks = queryBookmarkPort.getByStudentId(currentUserId).stream()
            .map(QueryStudentBookmarkResponse::from)
            .toList();
        return new QueryStudentBookmarksResponse(bookmarks);
    }
}
