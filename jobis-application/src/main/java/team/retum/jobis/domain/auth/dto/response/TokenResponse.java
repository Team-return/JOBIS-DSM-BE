package team.retum.jobis.domain.auth.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.auth.model.PlatformType;

import java.time.LocalDateTime;

@Getter
@Builder
public class TokenResponse {
    private final String accessToken;
    private final LocalDateTime accessExpiresAt;
    private final String refreshToken;
    private final LocalDateTime refreshExpiresAt;
    private final Authority authority;
    private final PlatformType platformType;
}
