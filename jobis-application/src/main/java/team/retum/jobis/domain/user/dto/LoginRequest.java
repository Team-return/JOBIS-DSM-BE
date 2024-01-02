package team.retum.jobis.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.auth.model.PlatformType;

@Getter
@Builder
public class LoginRequest {

    private final String accountId;

    private final String password;

    private final PlatformType platformType;
}