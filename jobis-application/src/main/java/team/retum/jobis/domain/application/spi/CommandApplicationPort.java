package team.retum.jobis.domain.application.spi;

import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationStatus;

import java.time.LocalDate;
import java.util.List;

public interface CommandApplicationPort {

    Application save(Application application);

    void deleteByIds(List<Long> applicationIds);

    void delete(Application application);

    void updateApplicationStatus(ApplicationStatus status, List<Long> applicationIds);

    void updateFieldTrainDate(LocalDate startDate, LocalDate endDate, List<Long> applicationIds);

    void saveAll(List<Application> applications);

    void deleteAllAttachmentByApplicationId(Long applicationId);
}
