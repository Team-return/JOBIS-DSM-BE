package team.retum.jobis.domain.auth.spi;

import team.retum.jobis.domain.auth.model.AuthCode;

public interface CommandAuthCodePort {

    void saveAuthCode(AuthCode authCode);
}
