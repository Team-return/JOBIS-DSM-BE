package team.retum.jobis.domain.auth.spi;

import team.retum.jobis.domain.auth.model.RefreshToken;

public interface CommandRefreshTokenPort {

    void saveRefreshToken(RefreshToken refreshToken);

}
