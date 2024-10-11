package team.retum.jobis.domain.bookmark.spi;

import team.retum.jobis.domain.bookmark.model.Bookmark;
import team.retum.jobis.domain.bookmark.spi.vo.BookmarkUserVO;
import team.retum.jobis.domain.bookmark.spi.vo.StudentBookmarksVO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface QueryBookmarkPort {

    boolean existsBookmarkByRecruitmentAndStudent(Long recruitmentId, Long studentId);

    Optional<Bookmark> getByRecruitmentIdAndStudentId(Long recruitmentId, Long studentId);

    List<StudentBookmarksVO> getByStudentId(Long studentId);

    Map<Long, List<BookmarkUserVO>> getBookmarkUserByRecruitmentIds(List<Long> recruitmentIds);
}
