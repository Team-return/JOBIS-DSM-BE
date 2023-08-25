package team.retum.jobis.global.security.auth;

import org.springframework.stereotype.Component;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.teacher.persistence.entity.TeacherEntity;

@Component
public class ThreadLocalService<T> {

    private final ThreadLocal<T> userThreadLocal = new ThreadLocal<>();

    public Object getUser() {
        return userThreadLocal.get();
    }

    public void setUser(T t) {
        userThreadLocal.set(t);
    }

    public Authority getAuthority() {
        Object currentUser = userThreadLocal.get();
        if (currentUser instanceof StudentEntity) {
            return Authority.STUDENT;
        } else if (currentUser instanceof CompanyEntity) {
            return Authority.COMPANY;
        } else if (currentUser instanceof TeacherEntity) {
            return Authority.TEACHER;
        } else {
            return null;
        }
    }

    public void remove() {
        userThreadLocal.remove();
    }
}
