package team.retum.jobis.domain.student.spi;

import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.student.model.SchoolNumber;
import team.retum.jobis.domain.student.model.Student;

import java.util.List;
import java.util.Optional;

public interface QueryStudentPort {
    Optional<Student> queryStudentById(Long studentId);

    boolean existsByGradeAndClassRoomAndNumberAndEntranceYear(int grade, int classRoom, int number, int entranceYear);

    int queryStudentCountByGradeAndEntranceYear(int grade, int entranceYear);

    boolean existsBySchoolNumberAndName(SchoolNumber schoolNumber, String name);

    Long queryStudentCountByApplicationStatus(List<ApplicationStatus> statuses);
}
