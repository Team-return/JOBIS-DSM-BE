package com.example.jobis.domain.company.facade;

import com.example.jobis.infrastructure.resttemplate.dto.response.BusinessNumberResponse;
import com.example.jobis.infrastructure.resttemplate.facade.RestTemplateFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CompanyFacade {

    private final RestTemplateFacade restTemplateFacade;

    public String getCompanyName(String businessNumber) {
        BusinessNumberResponse response = restTemplateFacade.getApi(businessNumber);
        return response.getBody().getItems().getItem().getCompany();
    }

    public boolean checkCompany(String businessNumber) {
        BusinessNumberResponse response = restTemplateFacade.getApi(businessNumber);

        return response.getBody().getItems().getItem().getBno().equals(businessNumber);
    }
}
