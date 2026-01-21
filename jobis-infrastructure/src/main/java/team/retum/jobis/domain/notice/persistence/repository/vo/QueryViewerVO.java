package team.retum.jobis.domain.notice.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.notice.spi.vo.ViewerVO;

import java.time.LocalDateTime;

@Getter
public class QueryViewerVO extends ViewerVO {

    @QueryProjection
    public QueryViewerVO(Long studentId, String studentName, Integer grade,
                         Integer classRoom, Integer number, LocalDateTime viewedAt) {
        super(studentId, studentName, grade, classRoom, number, viewedAt);
    }
}
