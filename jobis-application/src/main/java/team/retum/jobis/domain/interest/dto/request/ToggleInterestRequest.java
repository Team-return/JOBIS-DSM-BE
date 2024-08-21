package team.retum.jobis.domain.interest.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ToggleInterestRequest {

    private List<Long> codes;
}
