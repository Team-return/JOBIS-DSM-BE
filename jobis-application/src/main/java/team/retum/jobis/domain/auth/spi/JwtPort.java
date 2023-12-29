package team.retum.jobis.domain.auth.spi;

import team.retum.jobis.domain.auth.dto.TokenResponse;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.auth.model.PlatformType;

public interface JwtPort {

    Long getRefreshExp();

    TokenResponse generateTokens(Long userId, Authority authority, PlatformType platformType);
}
