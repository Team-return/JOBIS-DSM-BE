package team.retum.jobis.global.security.auth.teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.teacher.persistence.TeacherEntity;
import team.retum.jobis.domain.teacher.persistence.repository.TeacherRepository;
import team.retum.jobis.global.exception.InvalidTokenException;

@Component
@RequiredArgsConstructor
public class TeacherDetailsService implements UserDetailsService {
    private final TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String teacherId) throws UsernameNotFoundException {
        TeacherEntity teacherEntity = teacherRepository.queryTeacherById(
                Long.valueOf(teacherId)
        ).orElseThrow(() -> InvalidTokenException.EXCEPTION);

        return new TeacherDetails(teacherEntity.getId());
    }
}
