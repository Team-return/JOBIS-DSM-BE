package team.retum.jobis.domain.application.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import team.retum.jobis.domain.application.dto.request.CreateNonMOUPassApplicationsRequest;
import team.retum.jobis.global.annotation.ValidListElements;

import java.util.List;

@Getter
public class CreateNonMOUPassApplicationsWebRequest {

    @ValidListElements
    List<String> studentGcns;
}
