package com.example.jobis.domain.student.service;

import com.example.jobis.domain.student.controller.dto.request.StudentSignUpRequest;
import com.example.jobis.domain.student.domain.Student;
import com.example.jobis.domain.student.domain.repository.StudentRepository;
import com.example.jobis.domain.student.exception.StudentAlreadyExistsException;
import com.example.jobis.domain.student.facade.AuthCodeFacade;
import com.example.jobis.domain.student.facade.StudentFacade;
import com.example.jobis.domain.user.controller.dto.response.TokenResponse;
import com.example.jobis.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class StudentSignUpService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthCodeFacade authCodeFacade;
    private final StudentFacade studentFacade;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse execute(StudentSignUpRequest request) {

        if (studentFacade.existsEmail(request.getAccountId())) {
            throw StudentAlreadyExistsException.EXCEPTION;
        }
        authCodeFacade.checkIsVerified(request.getAccountId());

        Student student = studentRepository.save(Student.builder()
                .accountId(request.getAccountId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .grade(request.getGrade())
                .gender(request.getGender())
                .build());

        String accessToken = jwtTokenProvider.generateAccessToken(student.getAccountId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(student.getAccountId());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessExpiredAt(jwtTokenProvider.getExpiredAt())
                .build();
    }
}
