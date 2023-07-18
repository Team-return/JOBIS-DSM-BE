package team.retum.jobis.domain.recruitment.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.jobisapplication.domain.recruitment.domain.RecruitStatus;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChangeRecruitmentRequest {
    @NotNull
    private List<Long> recruitmentIds;

    @NotNull
    private RecruitStatus status;
}
