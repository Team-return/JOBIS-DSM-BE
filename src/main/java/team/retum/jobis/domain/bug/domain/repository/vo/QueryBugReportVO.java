package team.retum.jobis.domain.bug.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.bug.domain.enums.DevelopmentArea;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class QueryBugReportVO {

    private final String title;
    private final String content;
    private final DevelopmentArea developmentArea;
    private final List<String> attachments;
    private final LocalDateTime createdAt;

    @QueryProjection
    public QueryBugReportVO(String title, String content, DevelopmentArea developmentArea, List<String> attachments, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.developmentArea = developmentArea;
        this.attachments = attachments;
        this.createdAt = createdAt;
    }
}
