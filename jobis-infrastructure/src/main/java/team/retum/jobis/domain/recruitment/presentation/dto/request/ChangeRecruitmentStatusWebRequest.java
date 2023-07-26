package team.retum.jobis.domain.recruitment.presentation.dto.request;

import com.example.jobisapplication.domain.recruitment.dto.request.ChangeRecruitmentStatusRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.jobisapplication.domain.recruitment.model.RecruitStatus;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChangeRecruitmentStatusWebRequest {

    @NotNull
    private List<Long> recruitmentIds;

    @NotNull
    private RecruitStatus status;

    public ChangeRecruitmentStatusRequest toDomainRequest() {
        return ChangeRecruitmentStatusRequest.builder()
                .recruitmentIds(this.recruitmentIds)
                .status(this.status)
                .build();
    }
}
