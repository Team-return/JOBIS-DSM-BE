package team.retum.jobis.domain.bug.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.bug.dto.request.CreateBugReportRequest;
import team.retum.jobis.domain.bug.model.DevelopmentArea;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateBugReportWebRequest {

    @NotBlank
    @Size(max = 20)
    private String title;

    @NotBlank
    @Size(max = 400)
    private String content;

    @NotNull
    private DevelopmentArea developmentArea;

    private List<String> attachmentUrls;

    public CreateBugReportRequest toDomainRequest() {
        return CreateBugReportRequest.builder()
                .title(this.title)
                .content(this.content)
                .developmentArea(this.developmentArea)
                .attachmentUrls(this.attachmentUrls)
                .build();
    }
}
