package team.retum.jobis.domain.application.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.persistence.entity.ApplicationEntity;

import java.util.List;

public interface ApplicationJpaRepository extends JpaRepository<ApplicationEntity, Long> {

    List<ApplicationEntity> findAllByIdIn(List<Long> applicationIds);

    boolean existsByStudentIdAndApplicationStatusIn(Long studentId, List<ApplicationStatus> applicationStatuses);

    void deleteByIdIn(List<Long> applicationIds);
}
