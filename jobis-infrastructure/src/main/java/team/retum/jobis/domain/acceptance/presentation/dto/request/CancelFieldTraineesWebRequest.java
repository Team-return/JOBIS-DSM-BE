package team.retum.jobis.domain.acceptance.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.global.annotation.ValidListElements;

import java.util.List;

@Getter
@NoArgsConstructor
public class CancelFieldTraineesWebRequest {

    @ValidListElements
    private List<Long> applicationIds;
}
