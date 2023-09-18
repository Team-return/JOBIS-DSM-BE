package team.retum.jobis.domain.application.spi;

import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationStatus;

import java.time.LocalDate;
import java.util.List;

public interface CommandApplicationPort {

    Application saveApplication(Application application);

    void deleteApplicationByIds(List<Long> applicationIds);

    void deleteApplication(Application application);

    void changeApplicationStatus(ApplicationStatus status, List<Long> applicationIds);

    void updateFieldTrainDate(LocalDate startDate, LocalDate endDate, List<Long> applicationIds);

    void saveAllApplications(List<Application> applications);
}
