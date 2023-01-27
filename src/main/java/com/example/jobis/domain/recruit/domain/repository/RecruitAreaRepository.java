package com.example.jobis.domain.recruit.domain.repository;

import com.example.jobis.domain.recruit.domain.Recruitment;
import com.example.jobis.domain.recruit.domain.RecruitArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecruitAreaRepository extends JpaRepository<RecruitArea, Long> {

    List<RecruitArea> findAllByRecruitment(Recruitment recruitment);

    Optional<RecruitArea> findByIdAndRecruitment(Long id, Recruitment recruitment);
}
