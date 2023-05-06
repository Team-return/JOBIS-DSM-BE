package team.returm.jobis.domain.recruitment.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Getter
@NoArgsConstructor
public class RecruitAreaRequest {
    @NotNull
    private List<Long> jobCodes;

    @NotNull
    private List<Long> techCodes;

    @NotNull
    private int hiring;

    @NotBlank
    private String majorTask;

    public List<Long> getCodes() {
        return Stream.of(jobCodes, techCodes)
                .flatMap(Collection::stream)
                .toList();
    }
}
