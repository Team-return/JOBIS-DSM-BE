package com.example.jobis.domain.code.domain.repository;

import com.example.jobis.domain.code.domain.Code;
import com.example.jobis.domain.code.domain.RecruitAreaCode;
import com.example.jobis.domain.code.domain.RecruitAreaCodeId;
import com.example.jobis.domain.recruitment.domain.RecruitArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitAreaCodeRepository extends JpaRepository<RecruitAreaCode, RecruitAreaCodeId> {

    List<RecruitAreaCode> findAllByRecruitAreaId(RecruitArea recruitAreaId);

    void deleteByRecruitAreaIdAndCodeId(RecruitArea recruitAreaId, Code codeId);
}
