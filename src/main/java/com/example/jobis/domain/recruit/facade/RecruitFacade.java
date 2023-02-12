package com.example.jobis.domain.recruit.facade;

<<<<<<< HEAD
import com.example.jobis.domain.recruit.domain.Recruit;
import com.example.jobis.domain.recruit.domain.repository.RecruitRepository;
import com.example.jobis.domain.recruit.exception.RecruitNotFoundException;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
=======
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.recruit.domain.Recruitment;
import com.example.jobis.domain.recruit.domain.repository.RecruitmentJpaRepository;
import com.example.jobis.domain.recruit.domain.repository.RecruitmentRepository;
import com.example.jobis.domain.recruit.exception.RecruitNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;
>>>>>>> main

@RequiredArgsConstructor
@Component
public class RecruitFacade {

<<<<<<< HEAD
    private final RecruitRepository recruitRepository;

    public Recruit getRecruitById(Long id) {
        return recruitRepository.findById(id)
                .orElseThrow(() -> RecruitNotFoundException.EXCEPTION);
    }
=======
    private final RecruitmentJpaRepository recruitmentJpaRepository;

    public Recruitment getRecruitById(UUID id) {
        return recruitmentJpaRepository.findById(id)
                .orElseThrow(() -> RecruitNotFoundException.EXCEPTION);
    }

    public Recruitment getLatestRecruitByCompany(Company company) {
        return company.getRecruitmentList().get(company.getRecruitmentList().size() - 1);
    }
>>>>>>> main
}
