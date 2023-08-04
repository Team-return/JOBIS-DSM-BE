package team.retum.jobis.domain.bookmark.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.bookmark.spi.vo.StudentBookmarksVO;

import java.time.LocalDateTime;

@Getter
public class QueryStudentBookmarksVO extends StudentBookmarksVO {

    @QueryProjection
    public QueryStudentBookmarksVO(String companyName, Long recruitmentId, LocalDateTime createdAt) {
        super(companyName, recruitmentId, createdAt);
    }
}
