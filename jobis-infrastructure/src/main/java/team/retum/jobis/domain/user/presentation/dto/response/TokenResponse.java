package team.retum.jobis.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import com.example.jobisapplication.domain.auth.model.Authority;

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
