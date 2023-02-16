package com.example.jobis.domain.application.service;

import com.example.jobis.domain.application.controller.dto.request.CreateApplicationRequest;
import com.example.jobis.domain.application.domain.Application;
import com.example.jobis.domain.application.domain.ApplicationAttachment;
import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import com.example.jobis.domain.application.domain.repository.ApplicationRepository;
import com.example.jobis.domain.application.exception.ApplicationAlreadyExistsException;
import com.example.jobis.domain.application.exception.InvalidGradeException;
import com.example.jobis.domain.recruit.domain.Recruitment;
import com.example.jobis.domain.recruit.domain.repository.RecruitmentRepository;
import com.example.jobis.domain.student.domain.Student;
import com.example.jobis.domain.student.domain.types.Grade;
import com.example.jobis.domain.student.facade.StudentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CreateApplicationService {

    private final ApplicationRepository applicationRepository;
    private final StudentFacade studentFacade;
    private final RecruitmentRepository recruitmentRepository;

    public void execute(CreateApplicationRequest request, UUID recruitmentId) {

        Student student = studentFacade.getCurrentStudent();
        Recruitment recruitment = recruitmentRepository.findRecruitmentById(recruitmentId);

        if (applicationRepository.existsApplicationByStudentAndCompany(student, recruitment)) {
            throw ApplicationAlreadyExistsException.EXCEPTION;
        }

        if (!student.getGrade().equals(Grade.THIRD)) {
            throw InvalidGradeException.EXCEPTION;
        }

        if (applicationRepository.existsApplicationByStudentAndApplicationStatus(student, ApplicationStatus.APPROVED)) {
            throw ApplicationAlreadyExistsException.EXCEPTION;
        }

        Application application = applicationRepository.saveApplication(Application.builder()
                .student(student)
                .recruitment(recruitment)
                .applicationStatus(ApplicationStatus.REQUESTED)
                .build()
        );

        List<ApplicationAttachment> applicationAttachmentList = request.getAttachmentUrl()
                .stream()
                .map(a -> new ApplicationAttachment(a, application))
                .toList();
        applicationRepository.saveAllApplicationAttachment(applicationAttachmentList);
    }
}
