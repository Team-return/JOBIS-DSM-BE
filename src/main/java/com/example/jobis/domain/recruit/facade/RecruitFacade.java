package com.example.jobis.domain.recruit.facade;

import com.example.jobis.domain.recruit.domain.Recruit;
import com.example.jobis.domain.recruit.domain.repository.RecruitRepository;
import com.example.jobis.domain.recruit.exception.RecruitNotFoundException;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class RecruitFacade {

    private final RecruitRepository recruitRepository;

    public Recruit getRecruitById(Long id) {
        return recruitRepository.findById(id)
                .orElseThrow(() -> RecruitNotFoundException.EXCEPTION);
    }
}
