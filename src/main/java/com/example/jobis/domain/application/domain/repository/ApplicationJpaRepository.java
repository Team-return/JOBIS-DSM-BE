package com.example.jobis.domain.application.domain.repository;

import com.example.jobis.domain.application.domain.Application;
import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationJpaRepository extends JpaRepository<Application, Long> {

    boolean existsByStudentAndCompany(Student student, Company company);
    boolean existsByStudentAndApplicationStatus(Student student, ApplicationStatus applicationStatus);

    List<Application> findAllByStudent(Student student);
}
