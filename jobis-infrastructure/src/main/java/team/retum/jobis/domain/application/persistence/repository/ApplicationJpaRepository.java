package team.retum.jobis.domain.application.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.application.persistence.ApplicationEntity;
import com.example.jobisapplication.domain.application.domain.ApplicationStatus;
import team.retum.jobis.domain.student.persistence.StudentEntity;

import java.util.List;

public interface ApplicationJpaRepository extends JpaRepository<ApplicationEntity, Long> {

    boolean existsByStudentAndRecruitmentId(StudentEntity studentEntity, Long recruitmentId);

    boolean existsByStudentAndApplicationStatus(StudentEntity studentEntity, ApplicationStatus applicationStatus);

    List<ApplicationEntity> findAllByIdIn(List<Long> applicationIds);

    boolean existsByStudentIdAndApplicationStatusIn(Long studentId, List<ApplicationStatus> applicationStatuses);

    void deleteByIdIn(List<Long> applicationIds);
}
