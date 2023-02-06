package com.example.jobis.domain.company.service;

import com.example.jobis.domain.company.controller.dto.response.CompanyMyPageResponse;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.facade.CompanyFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class CompanyMyPageServiceTest {

    @MockBean
    private CompanyFacade companyFacade;
    private CompanyMyPageService companyMyPageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        companyMyPageService = new CompanyMyPageService(companyFacade);
    }

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