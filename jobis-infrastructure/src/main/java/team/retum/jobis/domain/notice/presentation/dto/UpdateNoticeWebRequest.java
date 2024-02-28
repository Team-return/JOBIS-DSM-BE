package team.retum.jobis.domain.notice.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateNoticeWebRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}