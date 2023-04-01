package team.returm.jobis.domain.student.domain.repository;

import team.returm.jobis.domain.student.domain.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class StudentRepository {
    private final StudentJpaRepository studentJpaRepository;

    public Optional<Student> queryStudentById(Long studentId) {
        return studentJpaRepository.findById(studentId);
    }
}
