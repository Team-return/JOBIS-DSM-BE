package team.returm.jobis.domain.student.domain.repository;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.returm.jobis.domain.student.domain.Student;

@RequiredArgsConstructor
@Repository
public class StudentRepository {

    private final StudentJpaRepository studentJpaRepository;

    public Optional<Student> queryStudentById(Long studentId) {
        return studentJpaRepository.findById(studentId);
    }
}
