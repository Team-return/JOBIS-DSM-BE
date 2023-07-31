package team.retum.jobis.domain.acceptance.presentation.dto.request;

import com.example.jobisapplication.domain.acceptance.dto.request.CancelFieldTraineesRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.global.annotation.ValidListElements;

import java.util.List;

@Getter
@NoArgsConstructor
public class CancelFieldTraineesWebRequest {

    @ValidListElements
    private List<Long> applicationIds;

    public CancelFieldTraineesRequest toDomainRequest() {
        return CancelFieldTraineesRequest.builder()
                .applicationIds(this.applicationIds)
                .build();
    }
}
