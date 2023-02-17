package com.example.jobis.domain.application.domain.repository;

import com.example.jobis.domain.application.domain.Application;
import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import com.example.jobis.domain.recruit.domain.Recruitment;
import com.example.jobis.domain.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ApplicationJpaRepository extends JpaRepository<Application, UUID> {

    boolean existsByStudentAndRecruitment(Student student, Recruitment recruitment);
    boolean existsByStudentAndApplicationStatus(Student student, ApplicationStatus applicationStatus);
    List<Application> findAllByStudent(Student student);
}
