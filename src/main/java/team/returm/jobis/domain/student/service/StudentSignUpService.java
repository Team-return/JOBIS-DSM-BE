package team.returm.jobis.domain.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import team.returm.jobis.domain.auth.facade.AuthCodeFacade;
import team.returm.jobis.domain.student.domain.Student;
import team.returm.jobis.domain.student.domain.repository.StudentJpaRepository;
import team.returm.jobis.domain.student.exception.StudentAlreadyExistsException;
import team.returm.jobis.domain.student.presentation.dto.request.StudentSignUpRequest;
import team.returm.jobis.domain.user.domain.User;
import team.returm.jobis.domain.user.domain.enums.Authority;
import team.returm.jobis.domain.user.domain.repository.UserRepository;
import team.returm.jobis.domain.user.presentation.dto.response.TokenResponse;
import team.returm.jobis.global.annotation.Service;
import team.returm.jobis.global.security.jwt.JwtTokenProvider;

@RequiredArgsConstructor
@Service
public class StudentSignUpService {

    private final StudentJpaRepository studentJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthCodeFacade authCodeFacade;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenResponse execute(StudentSignUpRequest request) {

        if (userRepository.existsByAccountId(request.getEmail())) {
            throw StudentAlreadyExistsException.EXCEPTION;
        }
        authCodeFacade.checkIsVerified(request.getEmail());

        User user = User.builder()
                .accountId(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .authority(Authority.STUDENT)
                .build();

        studentJpaRepository.save(
                Student.builder()
                        .phoneNumber(request.getPhoneNumber())
                        .user(user)
                        .classRoom(request.getClassRoom())
                        .number(request.getNumber())
                        .name(request.getName())
                        .gender(request.getGender())
                        .grade(request.getGrade())
                        .build()
        );

        String accessToken = jwtTokenProvider.generateAccessToken(user.getId(), user.getAuthority());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId(), user.getAuthority());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessExpiredAt(jwtTokenProvider.getExpiredAt())
                .build();
    }
}
