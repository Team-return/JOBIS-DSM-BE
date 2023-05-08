package team.returm.jobis.domain.bookmark.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QueryStudentBookmarksVO {

    private final String companyName;
    private final Long recruitmentId;
    private final LocalDateTime createdAt;

    @QueryProjection
    public QueryStudentBookmarksVO(String companyName, Long recruitmentId, LocalDateTime createdAt) {
        this.companyName = companyName;
        this.recruitmentId = recruitmentId;
        this.createdAt = createdAt;
    }
}
