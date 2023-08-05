package team.retum.jobis.domain.acceptance.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CancelFieldTraineesRequest {
    private List<Long> applicationIds;
}
