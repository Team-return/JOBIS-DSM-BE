package team.retum.jobis.domain.persistence.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.persistence.domain.enums.Authority;

import java.time.LocalDateTime;

@Getter
@Builder
public class TokenResponse {
    private final String accessToken;
    private final LocalDateTime accessExpiresAt;
    private final String refreshToken;
    private final LocalDateTime refreshExpiresAt;
    private final Authority authority;
}
