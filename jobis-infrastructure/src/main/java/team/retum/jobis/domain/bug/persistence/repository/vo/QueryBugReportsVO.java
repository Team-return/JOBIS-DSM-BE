package team.retum.jobis.domain.bug.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.bug.model.DevelopmentArea;
import team.retum.jobis.domain.bug.spi.vo.BugReportsVO;

import java.time.LocalDateTime;

@Getter
public class QueryBugReportsVO extends BugReportsVO {

    @QueryProjection
    public QueryBugReportsVO(Long id, String title, DevelopmentArea developmentArea, LocalDateTime createdAt) {
        super(id, title, developmentArea, createdAt);
    }
}
