package team.retum.jobis.domain.notice.spi;

import team.retum.jobis.domain.notice.spi.vo.ViewerVO;

import java.util.List;

public interface QueryViewLogPort {

    boolean existsByNoticeIdAndStudentId(Long noticeId, Long studentId);

    Long countByNoticeId(Long noticeId);

    List<ViewerVO> getViewersByNoticeId(Long noticeId);
}
