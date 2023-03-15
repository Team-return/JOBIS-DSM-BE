package com.example.jobis.domain.recruitment.facade;

import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.recruitment.domain.Recruitment;
import com.example.jobis.domain.recruitment.domain.repository.RecruitmentJpaRepository;
import com.example.jobis.domain.recruitment.exception.RecruitNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class RecruitFacade {

    private final RecruitmentJpaRepository recruitmentJpaRepository;

    public Recruitment queryRecruitmentById(UUID id) {
        return recruitmentJpaRepository.findById(id)
                .orElseThrow(() -> RecruitNotFoundException.EXCEPTION);
    }

    public Recruitment getLatestRecruitByCompany(Company company) {
        return company.getRecruitmentList().get(company.getRecruitmentList().size() - 1);
    }
}
