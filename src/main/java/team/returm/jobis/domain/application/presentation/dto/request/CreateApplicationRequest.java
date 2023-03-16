package team.returm.jobis.domain.application.presentation.dto.request;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateApplicationRequest {

    @NotNull
    private List<String> attachmentUrl;
}
