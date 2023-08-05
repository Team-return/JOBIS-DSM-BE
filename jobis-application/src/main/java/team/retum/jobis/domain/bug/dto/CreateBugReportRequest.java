package team.retum.jobis.domain.bug.dto;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.bug.model.DevelopmentArea;

import java.util.List;

@Getter
@Builder
public class CreateBugReportRequest {

    private String title;

    private String content;

    private DevelopmentArea developmentArea;

    private List<String> attachmentUrls;
}
