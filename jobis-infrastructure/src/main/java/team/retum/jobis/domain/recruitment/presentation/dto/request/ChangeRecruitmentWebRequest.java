package team.retum.jobis.domain.recruitment.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.jobisapplication.domain.recruitment.model.RecruitStatus;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChangeRecruitmentWebRequest {
    @NotNull
    private List<Long> recruitmentIds;

    @NotNull
    private RecruitStatus status;
}
