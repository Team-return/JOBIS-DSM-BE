package com.example.jobis.domain.application.service;

import com.example.jobis.domain.application.controller.dto.request.CreateApplicationRequest;
import com.example.jobis.domain.application.domain.Application;
import com.example.jobis.domain.application.domain.ApplicationAttachment;
import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import com.example.jobis.domain.application.domain.repository.ApplicationRepository;
import com.example.jobis.domain.application.exception.ApplicationAlreadyExistsException;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.facade.CompanyFacade;
import com.example.jobis.domain.student.domain.Student;
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
    private final CompanyFacade companyFacade;

    public void execute(CreateApplicationRequest request, UUID companyId) {

        Student student = studentFacade.getCurrentStudent();
        Company company = companyFacade.getCompanyById(companyId);

        if (applicationRepository.existsApplicationByStudentAndCompany(student, company)) {
            throw ApplicationAlreadyExistsException.EXCEPTION;
        }

        Application application = applicationRepository.saveApplication(Application.builder()
                .student(student)
                .company(company)
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
