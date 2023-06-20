package team.returm.jobis.domain.application.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.returm.jobis.domain.application.domain.Application;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import team.returm.jobis.domain.student.domain.Student;

import java.util.List;

public interface ApplicationJpaRepository extends JpaRepository<Application, Long> {

    boolean existsByStudentAndRecruitmentId(Student student, Long recruitmentId);

    boolean existsByStudentAndApplicationStatus(Student student, ApplicationStatus applicationStatus);

    List<Application> findAllByIdIn(List<Long> applicationIds);

    boolean existsByStudentIdAndApplicationStatusIn(Long studentId, List<ApplicationStatus> applicationStatuses);
}
