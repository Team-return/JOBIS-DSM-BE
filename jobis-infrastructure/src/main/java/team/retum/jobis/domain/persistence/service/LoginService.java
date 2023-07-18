package team.retum.jobis.domain.persistence.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import team.retum.jobis.domain.persistence.domain.User;
import team.retum.jobis.domain.persistence.exception.InvalidPasswordException;
import team.retum.jobis.domain.persistence.facade.UserFacade;
import team.retum.jobis.domain.persistence.presentation.dto.request.LoginRequest;
import team.retum.jobis.domain.persistence.presentation.dto.response.TokenResponse;
import team.retum.jobis.global.annotation.Service;
import team.retum.jobis.global.security.jwt.JwtTokenProvider;
import team.retum.jobis.global.security.jwt.TokenType;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenResponse execute(LoginRequest request) {

        User user = userFacade.getUser(request.getAccountId());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw InvalidPasswordException.EXCEPTION;
        }

        String accessToken = jwtTokenProvider.generateAccessToken(user.getId(), user.getAuthority());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId(), user.getAuthority());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .accessExpiresAt(jwtTokenProvider.getExpiredAt(TokenType.ACCESS))
                .refreshToken(refreshToken)
                .refreshExpiresAt(jwtTokenProvider.getExpiredAt(TokenType.REFRESH))
                .authority(user.getAuthority())
                .build();
    }
}
