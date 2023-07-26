package com.example.jobisapplication.domain.student.spi;

public interface QueryVerifiedStudentPort {
    boolean existsVerifiedStudentByGcnAndName(String gcn, String name);
}
