package team.returm.jobis.domain.user.presentation.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import team.returm.jobis.domain.user.domain.enums.Authority;

@Getter
@Builder
public class TokenResponse {
    private final String accessToken;
    private final LocalDateTime accessExpiresAt;
    private final String refreshToken;
    private final LocalDateTime refreshExpiresAt;
    private final Authority authority;
}
