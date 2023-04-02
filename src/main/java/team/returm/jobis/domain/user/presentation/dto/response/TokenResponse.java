package team.returm.jobis.domain.user.presentation.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenResponse {

    private String accessToken;
    private String refreshToken;
    private LocalDateTime accessExpiredAt;
}
