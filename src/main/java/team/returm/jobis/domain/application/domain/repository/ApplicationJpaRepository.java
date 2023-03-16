package team.returm.jobis.domain.application.domain.repository;

import team.returm.jobis.domain.application.domain.Application;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import team.returm.jobis.domain.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplicationJpaRepository extends JpaRepository<Application, UUID> {

    boolean existsByStudentAndRecruitmentId(Student student, UUID recruitmentId);
    boolean existsByStudentAndApplicationStatus(Student student, ApplicationStatus applicationStatus);
}
