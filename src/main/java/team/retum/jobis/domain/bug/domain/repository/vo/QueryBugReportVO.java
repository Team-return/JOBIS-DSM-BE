package team.retum.jobis.domain.bug.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.bug.domain.enums.DevelopmentArea;

import java.util.List;

@Getter
public class QueryBugReportVO {

    private final String title;
    private final String content;
    private final DevelopmentArea developmentArea;
    private final List<String> attachments;

    @QueryProjection
    public QueryBugReportVO(String title, String content, DevelopmentArea developmentArea, List<String> attachments) {
        this.title = title;
        this.content = content;
        this.developmentArea = developmentArea;
        this.attachments = attachments;
    }
}
