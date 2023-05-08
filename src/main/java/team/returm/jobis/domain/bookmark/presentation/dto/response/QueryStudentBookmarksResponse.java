package team.returm.jobis.domain.bookmark.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.returm.jobis.domain.bookmark.domain.repository.vo.QueryStudentBookmarksVO;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryStudentBookmarksResponse {

    private final List<QueryStudentBookmarksVO> bookmarks;
}
