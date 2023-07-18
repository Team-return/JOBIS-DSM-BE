package team.retum.jobis.global.security.auth.student;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.student.persistence.repository.StudentRepository;
import team.retum.jobis.global.exception.InvalidTokenException;

@Component
@RequiredArgsConstructor
public class StudentDetailsService implements UserDetailsService {
    private final StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String studentId) throws UsernameNotFoundException {
        StudentEntity studentEntity = studentRepository.queryStudentById(
                Long.valueOf(studentId)
        ).orElseThrow(() -> InvalidTokenException.EXCEPTION);

        return new StudentDetails(studentEntity.getId());
    }
}