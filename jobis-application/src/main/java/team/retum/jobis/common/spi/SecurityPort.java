package team.retum.jobis.common.spi;

import team.retum.jobis.domain.auth.model.Authority;

public interface SecurityPort {
    Long getCurrentUserId();

    String encodePassword(String password);

    Authority getCurrentUserAuthority();

    boolean isPasswordMatch(String rawPassword, String encodedPassword);
}
