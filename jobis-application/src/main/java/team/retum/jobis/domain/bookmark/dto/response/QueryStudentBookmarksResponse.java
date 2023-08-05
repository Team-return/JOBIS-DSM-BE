package team.retum.jobis.domain.bookmark.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.bookmark.spi.vo.StudentBookmarksVO;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryStudentBookmarksResponse {

    private final List<StudentBookmarksVO> bookmarks;
}
