package team.returm.jobis.domain.application.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.returm.jobis.domain.application.domain.Application;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import team.returm.jobis.domain.student.domain.Student;

import java.util.List;
import java.util.Optional;

public interface ApplicationJpaRepository extends JpaRepository<Application, Long> {

    boolean existsByStudentAndRecruitmentId(Student student, Long recruitmentId);

    boolean existsByStudentAndApplicationStatus(Student student, ApplicationStatus applicationStatus);

    List<Application> findAllByIdIn(List<Long> applicationIds);

    void deleteByIdIn(List<Long> applicationIds);

    Optional<Application> findByStudentId(Long studentId);

    boolean existsByIdAndApplicationStatusIn(Long applicationId, List<ApplicationStatus> applicationStatuses);
}
