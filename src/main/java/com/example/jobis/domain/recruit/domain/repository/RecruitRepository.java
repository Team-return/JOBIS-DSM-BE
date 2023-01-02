package com.example.jobis.domain.recruit.domain.repository;

import com.example.jobis.domain.recruit.domain.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecruitRepository extends JpaRepository<Recruit, Long> {
}
