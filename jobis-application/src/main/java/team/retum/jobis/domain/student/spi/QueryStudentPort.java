package team.retum.jobis.domain.student.spi;

import team.retum.jobis.domain.student.model.Student;

import java.util.Optional;

public interface QueryStudentPort {
    Optional<Student> queryStudentById(Long studentId);

    boolean existsByGradeAndClassRoomAndNumber(int grade, int classRoom, int number);

    int queryStudentCountByGradeAndEntranceYear(int grade, int entranceYear);

    boolean existsByGradeAndClassRoomAndNumberAndName(int grade, int classRoom, int number, String name);
}
