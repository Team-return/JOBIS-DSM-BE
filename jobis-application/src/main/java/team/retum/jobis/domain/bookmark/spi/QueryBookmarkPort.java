package team.retum.jobis.domain.bookmark.spi;

import team.retum.jobis.domain.bookmark.model.Bookmark;
import team.retum.jobis.domain.bookmark.spi.vo.BookmarkUserVO;
import team.retum.jobis.domain.bookmark.spi.vo.StudentBookmarksVO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface QueryBookmarkPort {

    boolean existsBookmarkByRecruitmentAndStudent(Long recruitmentId, Long studentId);

    Optional<Bookmark> queryBookmarkByRecruitmentIdAndStudentId(Long recruitmentId, Long studentId);

    List<StudentBookmarksVO> queryBookmarksByStudentId(Long studentId);

    Map<Long, List<BookmarkUserVO>> queryBookmarkUserByRecruitmentIds(List<Long> recruitmentIds);
}
