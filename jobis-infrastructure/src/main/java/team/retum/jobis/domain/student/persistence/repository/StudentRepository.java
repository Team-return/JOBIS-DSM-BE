package team.retum.jobis.domain.student.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class StudentRepository {

    private final StudentJpaRepository studentJpaRepository;

    public Optional<StudentEntity> queryStudentById(Long studentId) {
        return studentJpaRepository.findById(studentId);
    }

}
