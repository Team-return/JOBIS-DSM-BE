package com.example.jobis.domain.recruitment.facade;

import com.example.jobis.domain.recruitment.domain.RecruitArea;
import com.example.jobis.domain.recruitment.domain.Recruitment;
import com.example.jobis.domain.recruitment.domain.repository.RecruitAreaRepository;
import com.example.jobis.domain.recruitment.exception.RecruitAreaNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class RecruitAreaFacade {

    private final RecruitAreaRepository recruitAreaRepository;

    public RecruitArea getRecruitAreaById(UUID id) {
        return recruitAreaRepository.findById(id)
                .orElseThrow(() -> RecruitAreaNotFoundException.EXCEPTION);
    }

    public RecruitArea getRecruitAreaByIdAndRecruitment(UUID id, Recruitment recruitment) {
        return recruitAreaRepository.findByIdAndRecruitment(id, recruitment)
                .orElseThrow(() -> RecruitAreaNotFoundException.EXCEPTION);
    }
}
