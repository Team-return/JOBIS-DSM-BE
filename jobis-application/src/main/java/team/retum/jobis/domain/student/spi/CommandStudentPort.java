package team.retum.jobis.domain.student.spi;

import team.retum.jobis.domain.student.model.Student;

public interface CommandStudentPort {

    Student save(Student student);
}
