package team.retum.jobis.global.security.auth.teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.teacher.domain.Teacher;
import team.retum.jobis.domain.teacher.domain.repository.TeacherRepository;
import team.retum.jobis.domain.teacher.exception.TeacherNotFoundException;

@Component
@RequiredArgsConstructor
public class TeacherDetailsService implements UserDetailsService {
    private final TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String teacherId) throws UsernameNotFoundException {
        Teacher teacher = teacherRepository.queryTeacherById(
                Long.valueOf(teacherId)
        ).orElseThrow(() -> TeacherNotFoundException.EXCEPTION);

        return new TeacherDetails(teacher.getId());
    }
}
