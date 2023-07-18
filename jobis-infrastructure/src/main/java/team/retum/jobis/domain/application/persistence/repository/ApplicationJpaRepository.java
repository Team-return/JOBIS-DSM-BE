package team.retum.jobis.domain.application.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.application.persistence.Application;
import team.retum.jobis.domain.application.persistence.enums.ApplicationStatus;
import team.retum.jobis.domain.student.persistence.Student;

import java.util.List;

public interface ApplicationJpaRepository extends JpaRepository<Application, Long> {

    boolean existsByStudentAndRecruitmentId(Student student, Long recruitmentId);

    boolean existsByStudentAndApplicationStatus(Student student, ApplicationStatus applicationStatus);

    List<Application> findAllByIdIn(List<Long> applicationIds);

    boolean existsByStudentIdAndApplicationStatusIn(Long studentId, List<ApplicationStatus> applicationStatuses);

    void deleteByIdIn(List<Long> applicationIds);
}
