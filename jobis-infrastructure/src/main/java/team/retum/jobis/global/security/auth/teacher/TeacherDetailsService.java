package team.retum.jobis.global.security.auth.teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.teacher.persistence.entity.TeacherEntity;
import team.retum.jobis.domain.teacher.persistence.repository.TeacherJpaRepository;
import team.retum.jobis.global.exception.InvalidTokenException;

@Component
@RequiredArgsConstructor
public class TeacherDetailsService implements UserDetailsService {
    private final TeacherJpaRepository teacherJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String teacherId) throws UsernameNotFoundException {
        TeacherEntity teacherEntity = teacherJpaRepository.findById(
                Long.valueOf(teacherId)
        ).orElseThrow(() -> InvalidTokenException.EXCEPTION);

        return new TeacherDetails(teacherEntity.getId());
    }
}
