package team.retum.jobis.common.spi;

import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.student.model.Student;
import team.retum.jobis.domain.teacher.model.Teacher;
import team.retum.jobis.domain.user.model.User;

public interface SecurityPort {
    Long getCurrentUserId();

    String encodePassword(String password);

    Authority getCurrentUserAuthority();

    boolean isPasswordMatch(String rawPassword, String encodedPassword);

    Company getCurrentCompany();

    Student getCurrentStudent();

    Teacher getCurrentTeacher();

    User getCurrentUser();
}
