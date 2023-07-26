package com.example.jobisapplication.domain.student.spi;

public interface CommandVerifiedStudentPort {
    void deleteVerifiedStudentByGcnAndName(String gcn, String name);
}
