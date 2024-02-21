package team.retum.jobis.domain.acceptance.dto.request;

import java.util.List;

public record CancelFieldTraineesRequest(
        List<Long> applicationIds
) {}