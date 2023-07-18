package team.retum.jobis.domain.bug.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.bug.domain.enums.DevelopmentArea;

import java.time.LocalDateTime;

@Getter
public class QueryBugReportsVO {

    private final Long id;
    private final String title;
    private final DevelopmentArea developmentArea;
    private final LocalDateTime createdAt;

    @QueryProjection
    public QueryBugReportsVO(Long id, String title, DevelopmentArea developmentArea, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.developmentArea = developmentArea;
        this.createdAt = createdAt;
    }
}
