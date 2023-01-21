package com.example.jobis.domain.company.domain.repository;

import com.example.jobis.domain.company.controller.dto.response.CompanyResponse;
import com.example.jobis.domain.company.controller.dto.response.QCompanyResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.example.jobis.domain.company.domain.QCompany.company;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CompanyRepository {
    private final JPAQueryFactory queryFactory;

    public List<CompanyResponse> findCompanyInfoList() {
        return queryFactory
                .select(
                        new QCompanyResponse(
                             company.name,
                             company.companyLogoUrl,
                             company.sales
                        )
                )
                .from(company)
                .orderBy(company.name.desc())
                .fetch();
    }
}
