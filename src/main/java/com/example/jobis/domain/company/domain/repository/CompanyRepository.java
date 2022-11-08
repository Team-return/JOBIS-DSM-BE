package com.example.jobis.domain.company.domain.repository;

import com.example.jobis.domain.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByBusinessNumber(String num);
}
