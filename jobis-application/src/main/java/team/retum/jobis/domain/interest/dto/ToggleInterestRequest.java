package team.retum.jobis.domain.interest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ToggleInterestRequest {

    private List<Long> codes;
}
