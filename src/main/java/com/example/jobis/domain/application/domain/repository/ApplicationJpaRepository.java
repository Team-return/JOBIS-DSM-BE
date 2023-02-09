package com.example.jobis.domain.application.domain.repository;

import com.example.jobis.domain.application.domain.Application;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplicationJpaRepository extends JpaRepository<Application, UUID> {

    boolean existsByStudentAndCompany(Student student, Company company);
}
