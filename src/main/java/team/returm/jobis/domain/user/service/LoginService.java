package team.returm.jobis.domain.user.service;

import team.returm.jobis.domain.user.presentation.dto.request.LoginRequest;
import team.returm.jobis.domain.user.presentation.dto.response.UserAuthResponse;
import team.returm.jobis.domain.user.domain.User;
import team.returm.jobis.domain.user.exception.InvalidPasswordException;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.Service;
import team.returm.jobis.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserAuthResponse execute(LoginRequest request) {

        User user = userFacade.getUser(request.getAccountId());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw InvalidPasswordException.EXCEPTION;
        }

        String accessToken = jwtTokenProvider.generateAccessToken(user.getId(), user.getAuthority());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId(), user.getAuthority());

        return UserAuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessExpiresAt(jwtTokenProvider.getExpiredAt())
                .authority(user.getAuthority())
                .build();
    }
}
