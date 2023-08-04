package team.retum.jobis.domain.application.dto.request;

import lombok.Getter;
import team.retum.jobis.domain.application.model.ApplicationStatus;

import java.util.List;

@Getter
public class ChangeApplicationsStatusRequest {

    private List<Long> applicationIds;

    private ApplicationStatus status;
}
