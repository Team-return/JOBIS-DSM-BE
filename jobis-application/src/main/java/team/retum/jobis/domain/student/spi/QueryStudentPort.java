package team.retum.jobis.domain.student.spi;

import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.student.model.SchoolNumber;
import team.retum.jobis.domain.student.model.Student;

import java.util.List;

public interface QueryStudentPort {

    boolean existsByGradeAndClassRoomAndNumberAndEntranceYear(SchoolNumber schoolNumber, int entranceYear);

    int getCountByGradeAndEntranceYear(int grade, int entranceYear);

    boolean existsBySchoolNumberAndName(SchoolNumber schoolNumber, String name);

    Long getCountByApplicationStatus(List<ApplicationStatus> statuses);

    List<Student> getStudentByInterestCode(List<Long> code);

}
