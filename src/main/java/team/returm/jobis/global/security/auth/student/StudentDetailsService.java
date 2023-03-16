package team.returm.jobis.global.security.auth.student;

import team.returm.jobis.domain.student.domain.Student;
import team.returm.jobis.domain.student.domain.repository.StudentRepository;
import team.returm.jobis.domain.student.exception.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StudentDetailsService implements UserDetailsService {
    private final StudentRepository studentRepository;
    @Override
    public UserDetails loadUserByUsername(String studentId) throws UsernameNotFoundException {
        Student student = studentRepository.queryStudentById(
                UUID.fromString(studentId)
        ).orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        return new StudentDetails(student.getId());
    }
}
