package com.example.jobis.domain.recruit.domain.repository;

import com.example.jobis.domain.recruit.domain.Recruitment;
import com.example.jobis.domain.recruit.domain.RecruitArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecruitAreaRepository extends JpaRepository<RecruitArea, UUID> {

    Optional<RecruitArea> findByIdAndRecruitment(UUID id, Recruitment recruitment);
}
