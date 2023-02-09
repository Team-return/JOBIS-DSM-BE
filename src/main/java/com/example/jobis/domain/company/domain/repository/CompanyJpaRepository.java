package com.example.jobis.domain.company.domain.repository;

import com.example.jobis.domain.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyJpaRepository extends JpaRepository<Company, UUID> {
    Optional<Company> findByBizNo(String bizNo);

    boolean existsByBizNo(String bizNo);
}
