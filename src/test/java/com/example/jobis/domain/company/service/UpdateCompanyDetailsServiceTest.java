package com.example.jobis.domain.company.service;

import com.example.jobis.domain.company.presentation.dto.request.UpdateCompanyDetailsRequest;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.facade.CompanyFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UpdateCompanyDetailsServiceTest {
    @Mock
    private UpdateCompanyDetailsRequest updateCompanyDetailsRequest;
    @Mock
    private CompanyFacade companyFacade;
    @InjectMocks
    private UpdateCompanyDetailsService updateCompanyDetailsService;

    @Test
    void execute() {
        //given
        Company company = Company.builder()
                .workersCount(10)
                .sales(50)
                .email("before@gmail.com")
                .build();
        given(companyFacade.getCompany()).willReturn(company);
        given(updateCompanyDetailsRequest.getEmail()).willReturn("after@gmail.com");
        given(updateCompanyDetailsRequest.getTake()).willReturn(1000);
        given(updateCompanyDetailsRequest.getWorkerNumber()).willReturn(20);

        //when
        updateCompanyDetailsService.execute(updateCompanyDetailsRequest);

        //then
        assertEquals(company.getEmail(), "after@gmail.com");
    }
}