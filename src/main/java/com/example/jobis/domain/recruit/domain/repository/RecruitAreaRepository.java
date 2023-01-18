package com.example.jobis.domain.recruit.domain.repository;

import com.example.jobis.domain.recruit.domain.Recruitment;
import com.example.jobis.domain.recruit.domain.RecruitArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitAreaRepository extends JpaRepository<RecruitArea, Long> {

    List<RecruitArea> findAllByRecruit(Recruitment recruitment);
}
