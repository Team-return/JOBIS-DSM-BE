package com.example.jobis.domain.student.domain.repository;

import com.example.jobis.domain.student.domain.AuthCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthCodeRepository extends JpaRepository<AuthCode, String> {

}
