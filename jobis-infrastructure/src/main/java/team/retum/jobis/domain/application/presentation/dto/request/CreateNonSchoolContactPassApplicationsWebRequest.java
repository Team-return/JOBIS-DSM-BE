package team.retum.jobis.domain.application.presentation.dto.request;


import lombok.Getter;
import team.retum.jobis.global.annotation.ValidListElements;

import java.util.List;

@Getter
public class CreateNonSchoolContactPassApplicationsWebRequest {

    @ValidListElements
    List<String> studentGcns;
}
