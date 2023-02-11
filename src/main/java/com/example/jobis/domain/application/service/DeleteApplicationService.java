package com.example.jobis.domain.application.service;

import com.example.jobis.domain.application.domain.Application;
import com.example.jobis.domain.application.domain.repository.ApplicationRepository;
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
        Application application = applicationRepository.findApplicationByIdAndStudent(applicationId, student);

        applicationRepository.deleteApplication(application);
    }
}
