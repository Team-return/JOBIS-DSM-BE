package team.retum.jobis.domain.user.dto.request;

import team.retum.jobis.domain.auth.model.PlatformType;

public record LoginRequest(
        String accountId,
        String password,
        PlatformType platformType
) {
}