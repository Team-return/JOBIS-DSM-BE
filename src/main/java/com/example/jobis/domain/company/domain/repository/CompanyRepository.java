package com.example.jobis.domain.company.domain.repository;

import com.example.jobis.domain.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
