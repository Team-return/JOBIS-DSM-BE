package team.retum.jobis.domain.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import team.retum.jobis.domain.auth.persistence.entity.AuthCodeEntity;
import team.retum.jobis.domain.auth.persistence.repository.AuthCodeRepository;
import team.retum.jobis.domain.auth.exception.UnverifiedEmailException;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.student.persistence.repository.StudentJpaRepository;
import team.retum.jobis.domain.student.persistence.repository.VerifiedStudentRepository;
import team.retum.jobis.domain.student.exception.StudentAlreadyExistsException;
import team.retum.jobis.domain.student.presentation.dto.request.StudentSignUpRequest;
import team.retum.jobis.domain.user.persistence.entity.UserEntity;
import com.example.jobisapplication.domain.auth.domain.Authority;
import team.retum.jobis.domain.user.persistence.repository.UserRepository;
import team.retum.jobis.domain.user.presentation.dto.response.TokenResponse;
import com.example.jobisapplication.common.annotation.Service;
import team.retum.jobis.global.security.jwt.JwtTokenAdapter;
import team.retum.jobis.global.security.jwt.TokenType;

@RequiredArgsConstructor
@Service
public class StudentSignUpService {

    private final StudentJpaRepository studentJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthCodeRepository authCodeRepository;
    private final VerifiedStudentRepository verifiedStudentRepository;
    private final UserRepository userRepository;
    private final JwtTokenAdapter jwtTokenAdapter;

    public TokenResponse execute(StudentSignUpRequest request) {

        if (userRepository.existsByAccountId(request.getEmail())) {
            throw StudentAlreadyExistsException.EXCEPTION;
        }

        AuthCodeEntity authCodeEntity = authCodeRepository.findById(request.getEmail())
                .orElseThrow(() -> UnverifiedEmailException.EXCEPTION);
        authCodeEntity.checkIsVerified();

        if (studentJpaRepository.existsByGradeAndClassRoomAndNumber(
                request.getGrade(), request.getClassRoom(), request.getNumber())
        ) {
            throw StudentAlreadyExistsException.EXCEPTION;
        }

        UserEntity userEntity = UserEntity.builder()
                .accountId(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .authority(Authority.STUDENT)
                .build();

        StudentEntity studentEntity = studentJpaRepository.save(
                StudentEntity.builder()
                        .user(userEntity)
                        .classRoom(request.getClassRoom())
                        .number(request.getNumber())
                        .name(request.getName())
                        .gender(request.getGender())
                        .grade(request.getGrade())
                        .department(
                                StudentEntity.getDepartment(
                                        request.getGrade(),
                                        request.getClassRoom()
                                )
                        )
                        .profileImageUrl(request.getProfileImageUrl())
                        .build()
        );

        verifiedStudentRepository.deleteVerifiedStudentByGcnAndName(
                StudentEntity.processGcn(
                        studentEntity.getGrade(),
                        studentEntity.getClassRoom(),
                        studentEntity.getNumber()
                ),
                studentEntity.getName()
        );

        String accessToken = jwtTokenAdapter.generateAccessToken(userEntity.getId(), userEntity.getAuthority());
        String refreshToken = jwtTokenAdapter.generateRefreshToken(userEntity.getId(), userEntity.getAuthority());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .refreshExpiresAt(jwtTokenAdapter.getExpiredAt(TokenType.REFRESH))
                .accessExpiresAt(jwtTokenAdapter.getExpiredAt(TokenType.ACCESS))
                .authority(Authority.STUDENT)
                .build();
    }
}
