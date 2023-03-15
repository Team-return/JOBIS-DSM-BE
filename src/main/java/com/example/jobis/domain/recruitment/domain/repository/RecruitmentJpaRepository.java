package com.example.jobis.domain.recruitment.domain.repository;

import com.example.jobis.domain.recruitment.domain.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecruitmentJpaRepository extends JpaRepository<Recruitment, UUID> {
}
