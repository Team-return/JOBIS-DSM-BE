package com.example.jobisapplication.domain.application.spi;

import com.example.jobisapplication.domain.application.model.Application;
import com.example.jobisapplication.domain.application.model.ApplicationAttachment;
import com.example.jobisapplication.domain.application.model.ApplicationStatus;

import java.time.LocalDate;
import java.util.List;

public interface CommandApplicationPort {

    Application saveApplication(Application application);

    void deleteApplicationByIds(List<Long> applicationIds);

    void saveAllApplicationAttachment(List<ApplicationAttachment> applicationAttachments);

    void deleteApplication(Application application);

    void changeApplicationStatus(ApplicationStatus status, List<Long> applicationIds);

    void updateFieldTrainDate(LocalDate startDate, LocalDate endDate, List<Long> applicationIds);

    void saveAllApplications(List<Application> applications);
}
