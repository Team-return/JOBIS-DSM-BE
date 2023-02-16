package com.example.jobis.domain.code.domain.repository;

import com.example.jobis.domain.code.domain.Code;
import com.example.jobis.domain.code.domain.enums.CodeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CodeRepository extends JpaRepository<Code, Long> {
    List<Code> findByKeywordContainingAndCodeType(String keyword, CodeType codeType);

    @Query("select c from Code c where c.codeType = 'JOB'")
    List<Code> findAllJobCode();
}
