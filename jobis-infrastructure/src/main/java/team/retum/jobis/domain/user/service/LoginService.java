package team.retum.jobis.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import team.retum.jobis.domain.user.persistence.entity.UserEntity;
import com.example.jobisapplication.domain.user.exception.InvalidPasswordException;
import team.retum.jobis.domain.user.facade.UserFacade;
import team.retum.jobis.domain.user.presentation.dto.request.LoginWebRequest;
import com.example.jobisapplication.domain.user.dto.response.TokenResponse;
import com.example.jobisapplication.common.annotation.Service;
import team.retum.jobis.global.security.jwt.JwtTokenAdapter;
import team.retum.jobis.global.security.jwt.TokenType;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenAdapter jwtTokenAdapter;

    public TokenResponse execute(LoginWebRequest request) {

        UserEntity userEntity = userFacade.getUser(request.getAccountId());

        if (!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
            throw InvalidPasswordException.EXCEPTION;
        }

        String accessToken = jwtTokenAdapter.generateAccessToken(userEntity.getId(), userEntity.getAuthority());
        String refreshToken = jwtTokenAdapter.generateRefreshToken(userEntity.getId(), userEntity.getAuthority());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .accessExpiresAt(jwtTokenAdapter.getExpiredAt(TokenType.ACCESS))
                .refreshToken(refreshToken)
                .refreshExpiresAt(jwtTokenAdapter.getExpiredAt(TokenType.REFRESH))
                .authority(userEntity.getAuthority())
                .build();
    }
}
