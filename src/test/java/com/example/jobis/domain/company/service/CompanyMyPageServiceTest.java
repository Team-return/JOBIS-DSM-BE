package com.example.jobis.domain.company.service;

import com.example.jobis.domain.company.presentation.dto.response.CompanyMyPageResponse;
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
class CompanyMyPageServiceTest {

    @Mock
    private CompanyFacade companyFacade;
    @InjectMocks
    private CompanyMyPageService companyMyPageService;

    @Test
    void execute() {
        //given
        String expected = "0000";
        Company company = Company.builder().bizNo(expected).build();
        given(companyFacade.getCurrentCompany()).willReturn(company);

        //when
        CompanyMyPageResponse result = companyMyPageService.execute();

        //then
        assertEquals(result.getBizNo(), expected);
    }
}