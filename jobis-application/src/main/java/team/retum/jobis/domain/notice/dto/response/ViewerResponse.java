package team.retum.jobis.domain.notice.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.notice.spi.vo.ViewerVO;

import java.time.LocalDateTime;

@Getter
@Builder
public class ViewerResponse {

    private final Long studentId;
    private final String studentName;
    private final String studentGcn;
    private final LocalDateTime viewedAt;

    public static ViewerResponse from(ViewerVO vo) {
        return ViewerResponse.builder()
            .studentId(vo.getStudentId())
            .studentName(vo.getStudentName())
            .studentGcn(vo.getStudentGcn())
            .viewedAt(vo.getViewedAt())
            .build();
    }
}
