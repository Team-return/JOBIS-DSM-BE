package com.example.jobis.domain.application.domain.repository;

import com.example.jobis.domain.application.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationJpaRepository extends JpaRepository<Application, Long> {
}
