package team.retum.jobis.domain.student.spi;

import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.student.model.SchoolNumber;
import team.retum.jobis.domain.student.model.Student;
import team.retum.jobis.domain.student.spi.vo.TeacherStudentsVO;

import java.util.List;
import java.util.Optional;

public interface QueryStudentPort {

    Optional<Student> getById(Long id);

    boolean existsByGradeAndClassRoomAndNumberAndEntranceYear(SchoolNumber schoolNumber, int entranceYear);

    int getCountByGradeAndEntranceYear(int grade, int entranceYear);

    boolean existsBySchoolNumberAndName(SchoolNumber schoolNumber, String name);

    Long getCountByApplicationStatusAndYear(List<ApplicationStatus> statuses, int year);

    List<Student> getByInterestCode(List<Long> code);

    Long getTotalStudentsByClassNumber(Integer classNum, int year);

    Long getPassedStudentsByClassNumber(Integer classNum);

    List<Student> getStudentsByGradeAndClassRoomAndNumberAndEntranceYearOrThrow(List<SchoolNumber> schoolNumbers, int entranceYear);

    List<TeacherStudentsVO> getStudentsByName(String name);
}
