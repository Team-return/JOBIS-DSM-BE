package team.retum.jobis.domain.auth.spi;

import team.retum.jobis.domain.auth.model.RefreshToken;

public interface QueryRefreshTokenPort {

    RefreshToken queryRefreshTokenByToken(String token);

}
