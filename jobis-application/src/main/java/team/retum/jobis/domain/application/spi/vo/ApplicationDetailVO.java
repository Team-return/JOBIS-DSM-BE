package team.retum.jobis.domain.application.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.application.model.ApplicationStatus;

@Getter
@AllArgsConstructor
public class ApplicationDetailVO {

    private final Long id;

    private final Long studentId;

    private final Long companyId;

    private final String businessArea;

    private final ApplicationStatus status;
}
