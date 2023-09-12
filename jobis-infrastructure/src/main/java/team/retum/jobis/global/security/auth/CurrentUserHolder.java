package team.retum.jobis.global.security.auth;

import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.teacher.persistence.entity.TeacherEntity;

public class CurrentUserHolder {

    private static final ThreadLocal<Object> currentUser = new ThreadLocal<>();

    public static Object getUser() {
        return currentUser.get();
    }

    public static void setUser(Object obj) {
        currentUser.set(obj);
    }

    public static Authority getCurrentUserAuthority() {
        Object user = currentUser.get();
        if (user instanceof StudentEntity) {
            return Authority.STUDENT;
        } else if (user instanceof CompanyEntity) {
            return Authority.COMPANY;
        } else if (user instanceof TeacherEntity) {
            return Authority.TEACHER;
        } else {
            return null;
        }
    }

    public static void clear() {
        currentUser.remove();
    }
}
