package com.example.jobis.domain.recruit.facade;

import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.recruit.domain.Recruit;
import com.example.jobis.domain.recruit.domain.repository.RecruitRepository;
import com.example.jobis.domain.recruit.exception.RecruitNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RecruitFacade {

    private final RecruitRepository recruitRepository;

    public Recruit getRecruitById(Long id) {
        return recruitRepository.findById(id)
                .orElseThrow(() -> RecruitNotFoundException.EXCEPTION);
    }
}
