package team.retum.jobis.domain.application.dto.request;

import java.util.List;

public record CreateNonMOUPassApplicationsRequest(
    long recruitmentId,
    List<String> studentGcns
) {

}
