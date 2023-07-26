package com.example.jobisapplication.domain.recruitment.spi;

import com.example.jobisapplication.domain.code.model.RecruitAreaCode;
import com.example.jobisapplication.domain.recruitment.model.RecruitArea;
import com.example.jobisapplication.domain.recruitment.model.Recruitment;

import java.util.List;

public interface CommandRecruitmentPort {
    Recruitment saveRecruitment(Recruitment recruitment);

    RecruitArea saveRecruitmentArea(RecruitArea recruitArea);

    void saveAllRecruitmentAreaCodes(List<RecruitAreaCode> recruitAreaCodes);

    void saveAllRecruitments(List<Recruitment> recruitments);

    void deleteRecruitAreaById(Long recruitAreaId);

    void deleteRecruitment(Recruitment recruitment);
}
