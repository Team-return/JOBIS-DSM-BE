package com.example.jobis.domain.recruitment.domain.repository;

import com.example.jobis.domain.recruitment.domain.Recruitment;
import com.example.jobis.domain.recruitment.domain.RecruitArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RecruitAreaJpaRepository extends JpaRepository<RecruitArea, UUID> {

    Optional<RecruitArea> findByIdAndRecruitment(UUID id, Recruitment recruitment);
}
