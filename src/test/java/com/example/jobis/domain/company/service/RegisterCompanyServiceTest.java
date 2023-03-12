package com.example.jobis.domain.company.service;

import com.example.jobis.domain.company.presentation.dto.request.RegisterCompanyRequest;
import com.example.jobis.domain.company.domain.repository.CompanyRepository;
import com.example.jobis.domain.company.exception.CompanyAlreadyExistsException;
import com.example.jobis.domain.company.exception.CompanyNotFoundException;
import com.example.jobis.domain.company.facade.CompanyFacade;
import com.example.jobis.domain.user.domain.repository.UserRepository;
import com.example.jobis.global.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RegisterCompanyServiceTest {
    @Mock
    CompanyFacade companyFacade;

    @Mock
    CompanyRepository companyRepository;

    @Spy
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    JwtTokenProvider jwtTokenProvider;

    @Mock
    RegisterCompanyRequest request;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    RegisterCompanyService registerCompanyService;

    @Test
    void 이미_기업이_존재하는경우() {
        //given
        given(companyFacade.checkCompany("0000")).willReturn(true);
        given(companyFacade.companyExists("0000")).willReturn(true);
        given(request.getBusinessNumber()).willReturn("0000");

        //when
        assertThrows(CompanyAlreadyExistsException.class, () ->
                registerCompanyService.execute(request)
        );
    }

    @Test
    void 기업이_존재하지않는경우() {
        //given
        given(companyFacade.checkCompany("0000")).willReturn(false);
        given(request.getBusinessNumber()).willReturn("0000");

        //when
        assertThrows(CompanyNotFoundException.class, () ->
                registerCompanyService.execute(request)
        );
    }
}