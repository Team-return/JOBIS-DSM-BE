package team.returm.jobis.domain.acceptance.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.returm.jobis.global.annotation.ValidListElements;

import java.util.List;

@Getter
@NoArgsConstructor
public class CancelFieldTraineesRequest {

    @ValidListElements
    private List<Long> applicationIds;

}
