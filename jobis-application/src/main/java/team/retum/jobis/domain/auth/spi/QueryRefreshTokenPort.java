package team.retum.jobis.domain.auth.spi;

import team.retum.jobis.domain.auth.model.PlatformType;
import team.retum.jobis.domain.auth.model.RefreshToken;

public interface QueryRefreshTokenPort {

    RefreshToken queryRefreshTokenByTokenAndPlatformType(String token, PlatformType platformType);

}
