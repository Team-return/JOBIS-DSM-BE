package com.example.jobis.domain.application.service;

import com.example.jobis.domain.application.domain.Application;
import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import com.example.jobis.domain.application.domain.repository.ApplicationRepository;
import com.example.jobis.domain.application.exception.ApplicationAlreadyExistsException;
import com.example.jobis.domain.application.exception.InvalidStudentException;
import com.example.jobis.domain.student.domain.Student;
import com.example.jobis.domain.student.facade.StudentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DeleteApplicationService {

    private final ApplicationRepository applicationRepository;
    private final StudentFacade studentFacade;

    @Transactional
    public void execute(UUID applicationId) {

        Student student = studentFacade.getCurrentStudent();
        Application application = applicationRepository.findApplicationById(applicationId);
        ApplicationStatus status = application.getApplicationStatus();

        if (!status.equals(ApplicationStatus.REQUESTED)) {
            throw ApplicationAlreadyExistsException.EXCEPTION;
        }

        if (!application.getStudent().equals(student)) {
            throw InvalidStudentException.EXCEPTION;
        }

        applicationRepository.deleteApplication(application);
    }
}
