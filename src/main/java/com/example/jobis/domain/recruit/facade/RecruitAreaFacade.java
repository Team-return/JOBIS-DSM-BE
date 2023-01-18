package com.example.jobis.domain.recruit.facade;

import com.example.jobis.domain.recruit.domain.RecruitArea;
import com.example.jobis.domain.recruit.domain.repository.RecruitAreaRepository;
import com.example.jobis.domain.recruit.exception.RecruitAreaNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RecruitAreaFacade {

    private final RecruitAreaRepository recruitAreaRepository;

    public RecruitArea getRecruitAreaById(Long id) {
        return recruitAreaRepository.findById(id)
                .orElseThrow(() -> RecruitAreaNotFoundException.EXCEPTION);
    }
}
