package com.example.jobis.domain.user.service;

import com.example.jobis.domain.user.controller.dto.request.LoginRequest;
import com.example.jobis.domain.user.domain.User;
import com.example.jobis.domain.user.domain.enums.Authority;
import com.example.jobis.domain.user.exception.InvalidPasswordException;
import com.example.jobis.domain.user.facade.UserFacade;
import com.example.jobis.global.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @Mock
    UserFacade userFacade;

    @Mock
    JwtTokenProvider jwtTokenProvider;

    @Mock
    LoginRequest loginRequest;

    @Spy
    BCryptPasswordEncoder passwordEncoder;
    @InjectMocks
    LoginService loginService;
    User user;
    @BeforeEach
    void setUp() {
        user = User.builder()
                .authority(Authority.COMPANY)
                .accountId("id")
                .password(passwordEncoder.encode("password"))
                .build();
    }

    @Test
    void 로그인_성공() {
        //given
        given(userFacade.getUser("id")).willReturn(user);
        given(loginRequest.getAccountId()).willReturn("id");
        given(loginRequest.getPassword()).willReturn("password");

        //when then
        assertDoesNotThrow(() ->loginService.execute(loginRequest));
    }

    @Test
    void 잘못된_비밀번호() {
        //given
        given(userFacade.getUser("id")).willReturn(user);
        given(loginRequest.getAccountId()).willReturn("id");
        given(loginRequest.getPassword()).willReturn("wrongpassword");

        //when then
        assertThrows(InvalidPasswordException.class, () -> loginService.execute(loginRequest));

    }
}