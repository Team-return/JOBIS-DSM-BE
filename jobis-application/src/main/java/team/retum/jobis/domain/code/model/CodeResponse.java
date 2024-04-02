package team.retum.jobis.domain.code.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class CodeResponse {

    private final Long id;
    private final String name;
}
