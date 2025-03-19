package team.retum.jobis.domain.application.dto.request;

import java.util.List;

public record CreateNonSchoolContactPassApplicationsRequest(
    Long recruitmentId,
    List<String> studentGcns
) {

}
