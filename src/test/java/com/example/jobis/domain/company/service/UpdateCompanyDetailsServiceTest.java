package com.example.jobis.domain.company.service;

import com.example.jobis.domain.company.controller.dto.request.UpdateCompanyDetailsRequest;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.facade.CompanyFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.when;

@ExtendWith(SpringExtension.class)
class UpdateCompanyDetailsServiceTest {
    @MockBean
    private UpdateCompanyDetailsRequest updateCompanyDetailsRequest;

    @MockBean
    private CompanyFacade companyFacade;

    private UpdateCompanyDetailsService updateCompanyDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        updateCompanyDetailsService = new UpdateCompanyDetailsService(companyFacade);
    }
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