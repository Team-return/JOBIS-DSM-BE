package com.example.jobis.domain.code.domain.repository;

import com.example.jobis.domain.code.domain.RecruitAreaCode;
import com.example.jobis.domain.code.domain.RecruitAreaCodeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecruitAreaCodeJpaRepository extends JpaRepository<RecruitAreaCode, RecruitAreaCodeId> {
    void deleteAllByRecruitAreaId(UUID recruitAreaId);
}
