package team.retum.jobis.domain.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class QueryEmploymentCountResponse {

    private final int totalStudentCount;
    private final long passedCount;
    private final long approvedCount;

}
