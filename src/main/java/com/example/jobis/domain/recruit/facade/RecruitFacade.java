package com.example.jobis.domain.recruit.facade;

import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.recruit.domain.Recruitment;
import com.example.jobis.domain.recruit.domain.repository.RecruitmentJpaRepository;
import com.example.jobis.domain.recruit.domain.repository.RecruitmentRepository;
import com.example.jobis.domain.recruit.exception.RecruitNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RecruitFacade {

    private final RecruitmentJpaRepository recruitmentJpaRepository;

    public Recruitment getRecruitById(Long id) {
        return recruitmentJpaRepository.findById(id)
                .orElseThrow(() -> RecruitNotFoundException.EXCEPTION);
    }

    public Recruitment getLatestRecruitByCompany(Company company) {
        return company.getRecruitmentList().get(company.getRecruitmentList().size() - 1);
    }
}
