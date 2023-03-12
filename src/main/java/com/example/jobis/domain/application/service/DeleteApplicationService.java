package com.example.jobis.domain.application.service;

import com.example.jobis.domain.application.domain.Application;
import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import com.example.jobis.domain.application.domain.repository.ApplicationRepository;
import com.example.jobis.domain.application.exception.ApplicationCannotDeleteException;
import com.example.jobis.domain.application.exception.ApplicationNotFoundException;
import com.example.jobis.domain.application.exception.InvalidStudentException;
import com.example.jobis.domain.recruit.domain.Recruitment;
import com.example.jobis.domain.student.domain.Student;
import com.example.jobis.domain.user.facade.UserFacade;
import com.example.jobis.global.annotation.Service;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DeleteApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserFacade userFacade;

    public void execute(UUID applicationId) {
        Student student = userFacade.getCurrentStudent();
        Application application = applicationRepository.queryApplicationById(applicationId)
                .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION);

        if (!application.getStudent().equals(student)) {
            throw InvalidStudentException.EXCEPTION;
        }

        if (!application.getApplicationStatus().equals(ApplicationStatus.REQUESTED)) {
            throw ApplicationCannotDeleteException.EXCEPTION;
        }

        applicationRepository.deleteApplication(application);
    }
}
