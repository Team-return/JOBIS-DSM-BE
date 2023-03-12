package com.example.jobis.domain.student.domain.repository;

import com.example.jobis.domain.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentJpaRepository extends JpaRepository<Student, UUID> {

    boolean existsByEmail(String email);

    Optional<Student> findByEmail(String email);
}
